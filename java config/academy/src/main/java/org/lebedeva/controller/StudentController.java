package org.lebedeva.controller;

import lombok.extern.slf4j.Slf4j;
import org.lebedeva.dto.GroupDto;
import org.lebedeva.dto.StudentDto;
import org.lebedeva.model.Student;
import org.lebedeva.object.FileUploadModel;
import org.lebedeva.service.GroupService;
import org.lebedeva.service.StudentService;
import org.lebedeva.service.UploadFileService;
import org.lebedeva.validator.MultipartFileValidator;
import org.lebedeva.validator.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping(StudentController.BASE_URL)
public class StudentController {

    public static final String VIEW_PATH = "student";
    public static final String BASE_URL = "/students";
    public static final String FORM_PATH = VIEW_PATH + "/form";
    public static final String EDIT_PATH = VIEW_PATH + "/edit";
    public static final String INDEX_PATH = VIEW_PATH + "/index";
    public static final String UPLOADS_DIR = "/uploads/students";
    public static final String REDIRECT_INDEX = "redirect:" + BASE_URL;
    public static final String REGISTER_PATH = VIEW_PATH + "/registration";

    private Pageable pageable;

    private final GroupService groupService;
    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;
    private final StudentValidator studentValidator;
    private final UploadFileService uploadFileService;
    private final MultipartFileValidator fileValidator;

    @Autowired
    public StudentController(GroupService groupService,
                             StudentService studentService,
                             StudentValidator studentValidator,
                             UploadFileService uploadFileService,
                             MultipartFileValidator fileValidator) {
        this.groupService = groupService;
        this.fileValidator = fileValidator;
        this.studentService = studentService;
        this.studentValidator = studentValidator;
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
        Page<StudentDto> dtoPage = studentService.findAll(pageable);
        model.addAttribute("students", dtoPage.getContent());
        model.addAttribute("page", dtoPage);
        model.addAttribute("url", BASE_URL);
        return INDEX_PATH;
    }

    @GetMapping("/search")
    public String search(@RequestParam String search, Model model, Integer page, Integer size) {
        pageable = PageRequest.of(page == null ? 0 : page, size == null ? 5 : size);
        Page<StudentDto> dtoPage = studentService.findStudentsDtoByNameOrSurname(search, pageable);
        model.addAttribute("students", dtoPage.getContent());
        model.addAttribute("find", "&search=" + search);
        model.addAttribute("url", BASE_URL + "/search");
        model.addAttribute("page", dtoPage);
        return INDEX_PATH;
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("studentDto", new StudentDto());
        return FORM_PATH;
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @PostMapping("/create")
    public String create(@Validated @ModelAttribute StudentDto studentDto,
                         BindingResult result,
                         RedirectAttributes attributes,
                         MultipartFile img) {

        fileValidator.validate(new FileUploadModel(img), result);
        studentValidator.validate(studentDto, result);

        if (!result.hasErrors()) {
            try {
                studentDto.setPassword(passwordEncoder.encode(studentDto.getPassword()));

                if (!img.isEmpty()) {
                    studentDto.setPhoto(img.getOriginalFilename());
                    uploadFileService.uploadFile(img, UPLOADS_DIR, studentService.save(studentDto).getId());
                } else {
                    studentService.save(studentDto);
                }
                attributes.addFlashAttribute("message", "Saved successfully!");
            } catch (Exception ex) {
                attributes.addFlashAttribute("message", "Saving failed. Email already exists!");
                log.error(ex.getLocalizedMessage(), ex);
            }
            return REDIRECT_INDEX;
        }
        return FORM_PATH;
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            studentService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Deletion failed!");
            log.error(e.getLocalizedMessage(), e);
        }
        return REDIRECT_INDEX;
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        try {
            model.addAttribute("studentDto", studentService.findById(id).orElseThrow(Exception::new));
            return EDIT_PATH;
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return REDIRECT_INDEX;
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    @PostMapping("/edit/{id}")
    public String edit(@Validated @ModelAttribute StudentDto studentDto,
                       BindingResult result,
                       RedirectAttributes attributes,
                       MultipartFile img) {

        fileValidator.validate(new FileUploadModel(img), result);
        studentValidator.validate(studentDto, result);

        if (!result.hasErrors()) {
            try {
                if (!img.isEmpty()) {
                    studentDto.setPhoto(img.getOriginalFilename());
                    uploadFileService.uploadFile(img, UPLOADS_DIR, studentService.save(studentDto).getId());
                } else {
                    studentService.save(studentDto);
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

    @GetMapping("/register")
    public String goRegister(Model model) {
        model.addAttribute("studentDto", new StudentDto());
        return REGISTER_PATH;
    }

    @PostMapping("/register")
    public String doRegister(@Validated @ModelAttribute StudentDto studentDto,
                             BindingResult result,
                             RedirectAttributes attributes,
                             MultipartFile img) {

        fileValidator.validate(new FileUploadModel(img), result);
        studentValidator.validate(studentDto, result);

        if (!result.hasErrors()) {
            try {
                studentDto.setPassword(passwordEncoder.encode(studentDto.getPassword()));
                Student student;

                if (!img.isEmpty()) {
                    studentDto.setPhoto(img.getOriginalFilename());
                    student = studentService.save(studentDto);
                    uploadFileService.uploadFile(img, UPLOADS_DIR, student.getId());
                } else {
                    student = studentService.save(studentDto);
                }
                attributes.addFlashAttribute("message", "Registered successfully!");

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        student, studentDto.getPassword(), student.getAuthorities());

                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);

            } catch (Exception ex) {
                attributes.addFlashAttribute("message", "Register failed. Email already exists!");
                log.error(ex.getLocalizedMessage(), ex);
            }
            return "redirect:/";
        }
        log.info(result.toString());
        return REGISTER_PATH;
    }
}
