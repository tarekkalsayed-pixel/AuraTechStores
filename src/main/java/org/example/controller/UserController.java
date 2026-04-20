package org.example.controller;

import org.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/")
    public String gotoLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        if (validator.validateUser(user) == false) {
            model.addAttribute("username", user.getUsername());
            return "error";
        }

        if (user.getUsertype().equals("User")) {
            model.addAttribute("username", user.getUsername());
            return "user";
        } else if (user.getUsertype().equals("admin")) {
            model.addAttribute("username", user.getUsername());
            return "admin";
        }

        return "login";
    }
}
