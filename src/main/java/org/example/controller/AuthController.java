package org.example.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.model.User;
import org.example.service.HistoryService;
import org.example.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;
    private final HistoryService historyService;

    AuthController(UserService userService, HistoryService historyService) {
        this.userService = userService;
        this.historyService = historyService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/products";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        String email = user.getEmail().trim().toLowerCase();
        user.setEmail(email);
        if (!user.getEmail().endsWith("@gmail.com")) {
            model.addAttribute("error", "Email must end with @gmail.com");
            return "register";
        }
        if (userService.emailExists(email)) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }
        user.setRole("USER");
        userService.save(user);
        historyService.add(user.getUsername(), "REGISTER", "New user account");
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Authentication auth, HttpSession session) {
        User user = userService.findByUsername(auth.getName());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());
        if ("ADMIN".equals(user.getRole())) {
            return "redirect:/admin/home";
        }
        return "redirect:/user/home";
    }
}
