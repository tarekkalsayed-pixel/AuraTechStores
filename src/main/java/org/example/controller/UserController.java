package org.example.controller;

import org.example.model.User;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserService userService;
    private final ProductService productService;

    UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/user/home")
    public String userHome(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "user-home";
    }

    @GetMapping("/user/profile")
    public String profile(Authentication auth, Model model) {
        User user = userService.findByUsername(auth.getName());
        model.addAttribute("user", user);
        return "profile-user";
    }
}
