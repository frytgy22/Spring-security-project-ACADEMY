package org.lebedeva.controller;

import lombok.extern.slf4j.Slf4j;
import org.lebedeva.dto.GroupDto;
import org.lebedeva.dto.TeacherDto;
import org.lebedeva.object.FileUploadModel;
import org.lebedeva.service.GroupService;
import org.lebedeva.service.TeacherService;
import org.lebedeva.service.UploadFileService;
import org.lebedeva.validator.MultipartFileValidator;
import org.lebedeva.validator.TeacherValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(TeacherController.BASE_URL)
public class TeacherController {

    public static final String VIEW_PATH = "teacher";
    public static final String BASE_URL = "/teachers";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String EDIT_PATH = VIEW_PATH + "/edit";
    public static final String INDEX_PATH = VIEW_PATH + "/index";
    public static final String UPLOADS_DIR = "/uploads/teachers";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;

    private Pageable pageable;

    private final GroupService groupService;
    private final TeacherService teacherService;
    private final PasswordEncoder passwordEncoder;
    private final TeacherValidator teacherValidator;
    private final UploadFileService uploadFileService;
    private final MultipartFileValidator fileValidator;

    @Autowired
    public TeacherController(GroupService groupService,
                             TeacherService teacherService,
                             TeacherValidator teacherValidator,
                             UploadFileService uploadFileService,
                             MultipartFileValidator fileValidator) {
        this.groupService = groupService;
        this.fileValidator = fileValidator;
        this.teacherService = teacherService;
        this.teacherValidator = teacherValidator;
        this.uploadFileService = uploadFileService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @ModelAttribute("groups")
    List<GroupDto> getGroups() {
        return groupService.findGroupsDto();
    }

    @ModelAttribute("message")
    public void setMessage() {
    }

    @GetMapping
    public String index(Model model, Integer page, Integer size) {
        pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size);
        Page<TeacherDto> dtoPage = teacherService.findAll(pageable);
        model.addAttribute("teachers", dtoPage.getContent());
        model.addAttribute("url", BASE_URL);
        model.addAttribute("page", dtoPage);
        return INDEX_PATH;
    }

    @GetMapping("/search")
    public String search(@RequestParam String search, Model model, Integer page, Integer size) {
        pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size);
        Page<TeacherDto> dtoPage = teacherService.findTeachersDtoByNameOrSurname(search, pageable);
        model.addAttribute("teachers", dtoPage.getContent());
        model.addAttribute("find", "&search=" + search);
        model.addAttribute("url", BASE_URL + "/search");
        model.addAttribute("page", dtoPage);
        return INDEX_PATH;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("teacherDto", new TeacherDto());
        return FORM_PATH;
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/create")
    public String create(@Validated @ModelAttribute TeacherDto teacherDto,
                         BindingResult result,
                         RedirectAttributes attributes,
                         MultipartFile img) {

        fileValidator.validate(new FileUploadModel(img), result);
        teacherValidator.validate(teacherDto, result);

        if (!result.hasErrors()) {
            try {
                teacherDto.setPassword(passwordEncoder.encode(teacherDto.getPassword()));

                if (!img.isEmpty()) {
                    teacherDto.setPhoto(img.getOriginalFilename());
                    uploadFileService.uploadFile(img, UPLOADS_DIR, teacherService.save(teacherDto).getId());
                } else {
                    teacherService.save(teacherDto);
                }
                attributes.addFlashAttribute("message", "Saved successfully!");
            } catch (Exception ex) {
                attributes.addFlashAttribute("message", "Saving failed. Email already exists!");
                log.error(ex.getLocalizedMessage(), ex);
            }
            return REDIRECT_INDEX;
        }
        log.error(result.toString());
        return FORM_PATH;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            teacherService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Deletion failed!");
            log.error(e.getLocalizedMessage(), e);
        }
        return REDIRECT_INDEX;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        try {
            model.addAttribute("teacherDto", teacherService.findById(id).orElseThrow(Exception::new));
            return EDIT_PATH;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return REDIRECT_INDEX;
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/edit/{id}")
    public String edit(@Validated @ModelAttribute TeacherDto teacherDto,
                       BindingResult result,
                       RedirectAttributes attributes,
                       MultipartFile img) {

        fileValidator.validate(new FileUploadModel(img), result);
        teacherValidator.validate(teacherDto, result);

        if (!result.hasErrors()) {
            try {
                if (!img.isEmpty()) {
                    teacherDto.setPhoto(img.getOriginalFilename());
                    uploadFileService.uploadFile(img, UPLOADS_DIR, teacherService.save(teacherDto).getId());
                } else {
                    teacherService.save(teacherDto);
                }
                attributes.addFlashAttribute("message", "Updated successfully!");
            } catch (Exception ex) {
                attributes.addFlashAttribute("message", "Update failed. Email already exists!");
                log.error(ex.getLocalizedMessage(), ex);
            }
            return REDIRECT_INDEX;
        }
        return EDIT_PATH;
    }
}
