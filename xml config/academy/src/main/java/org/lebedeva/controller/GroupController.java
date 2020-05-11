package org.lebedeva.controller;

import lombok.extern.slf4j.Slf4j;
import org.lebedeva.dto.GroupDto;
import org.lebedeva.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping(GroupController.BASE_URL)
public class GroupController {

    public static final String VIEW_PATH = "group";
    public static final String BASE_URL = "/groups";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String INDEX_PATH = VIEW_PATH + "/index";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;

    private Pageable pageable;
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ModelAttribute("message")
    public void setMessage() {
    }

    @GetMapping
    public String index(Model model, Integer page, Integer size) {
        pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size);
        Page<GroupDto> dtoPage = groupService.findAll(pageable);
        model.addAttribute("groups", dtoPage.getContent());
        model.addAttribute("page", dtoPage);
        model.addAttribute("url", BASE_URL);
        return INDEX_PATH;
    }

    @GetMapping("/search")
    public String search(String search, Model model, Integer page, Integer size) {
        pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size);
        Page<GroupDto> dtoPage = groupService.findGroupsDtoByName(search, pageable);
        model.addAttribute("groups", dtoPage.getContent());
        model.addAttribute("find", "&search=" + search);
        model.addAttribute("url", BASE_URL + "/search");
        model.addAttribute("page", dtoPage);
        return INDEX_PATH;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("groupDto", new GroupDto());
        return FORM_PATH;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute GroupDto groupDto,
                         BindingResult bindingResult,
                         RedirectAttributes attributes) {
        if (!bindingResult.hasErrors()) {
            try {
                groupService.save(groupDto);
                attributes.addFlashAttribute("message", "Saved successfully!");
            } catch (Exception ex) {
                attributes.addFlashAttribute("message", "Saving failed. Group already exists!");
                log.error(ex.getLocalizedMessage(), ex);
            }
            return REDIRECT_INDEX;
        }
        log.error(bindingResult.toString());
        return FORM_PATH;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            groupService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Deletion failed!");
            log.error(e.getLocalizedMessage(), e);
        }
        return REDIRECT_INDEX;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        try {
            model.addAttribute("groupDto", groupService.findById(id).orElseThrow(Exception::new));
            return FORM_PATH;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return REDIRECT_INDEX;
    }

    @PostMapping("/edit/{id}")
    public String edit(@Validated @ModelAttribute GroupDto groupDto,
                       BindingResult bindingResult,
                       RedirectAttributes attributes) {
        return create(groupDto, bindingResult, attributes);
    }
}
