package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.Product;
import org.example.model.User;
import org.example.service.EmailService;
import org.example.service.HistoryService;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private final ProductService productService;
    private final HistoryService historyService;
    private final EmailService emailService;
    private final UserService userService;

    AdminController(ProductService productService,
                    HistoryService historyService,
                    EmailService emailService,
                    UserService userService) {
        this.productService = productService;
        this.historyService = historyService;
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/admin/home")
    public String adminHome(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin-home";
    }

    @GetMapping("/admin/profile")
    public String profile(Authentication auth, Model model) {
        User user = userService.findByUsername(auth.getName());
        model.addAttribute("user", user);
        return "profile-admin";
    }

    @GetMapping("/admin/products/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @GetMapping("/admin/products/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "product-form";
    }

    @PostMapping("/admin/products/save")
    public String save(@Valid @ModelAttribute Product product,
                       BindingResult result,
                       Authentication auth) {
        if (result.hasErrors()) {
            return "product-form";
        }
        productService.saveProduct(product);
        historyService.add(auth.getName(), "SAVE", product.getName());
        return "redirect:/admin/home";
    }

    @GetMapping("/admin/products/delete/{id}")
    public String delete(@PathVariable Long id, Authentication auth) {
        productService.deleteProduct(id);
        historyService.add(auth.getName(), "DELETE", "Product id " + id);
        return "redirect:/admin/home";
    }

    @PostMapping("/admin/send-email")
    public String sendEmail(@RequestParam String email,
                            @RequestParam String productName) {
        emailService.sendOffer(email, productName);
        return "redirect:/admin/history";
    }

    @GetMapping("/admin/history")
    public String history(Model model) {
        model.addAttribute("history", historyService.getAll());
        return "history";
    }
}
