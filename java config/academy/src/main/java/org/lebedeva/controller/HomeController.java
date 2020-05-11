package org.lebedeva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("message");
        return "index";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/favicon.ico")
    String favicon() {
        return "forward:/static/images/icon.png";
    }

    @GetMapping("/login")
    public String goLogin() {
        return "login";
    }

    @GetMapping("/403")
    public String errorPage() {
        return "error";
    }
}

