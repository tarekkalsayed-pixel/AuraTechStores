package org.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(@RequestParam(required = false) String branch,
                           @CookieValue(value = "lastBranch", defaultValue = "None") String lastBranch,
                           HttpServletResponse response,
                           Model model) {
        List<Product> products;
        if (branch == null || branch.isBlank()) {
            products = productService.getAllProducts();
        } else {
            products = productService.getProductsByBranch(branch);
            response.addCookie(new Cookie("lastBranch", branch));
            lastBranch = branch;
        }
        model.addAttribute("products", products);
        model.addAttribute("lastBranch", lastBranch);
        model.addAttribute("selectedBranch", branch);
        return "products";
    }
}
