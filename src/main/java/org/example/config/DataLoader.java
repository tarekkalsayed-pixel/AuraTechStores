package org.example.config;

import org.example.model.Product;
import org.example.model.User;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;

    DataLoader(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        addUsers();
        addProducts();
    }

    private void addUsers() {
        if (userService.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("12345");
            admin.setEmail("admin@aura.com");
            admin.setRole("ADMIN");
            userService.save(admin);
        }
        if (userService.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword("12345");
            user.setEmail("user@aura.com");
            user.setRole("USER");
            userService.save(user);
        }
    }

    private void addProducts() {
        if (!productService.getAllProducts().isEmpty()) {
            return;
        }
        save("Aura Phone", "Phones", 33000, "Cairo", 6, "/images/phone.svg");
        save("AuraBook Air", "Laptops", 58000, "Alexandria", 4, "/images/laptop.svg");
        save("Aura Pods", "Audio", 7200, "Cairo", 12, "/images/headphones.svg");
        save("Aura Watch", "Wearables", 12500, "Alexandria", 7, "/images/watch.svg");
    }

    private void save(String name, String category, double price,
                      String branch, int stock, String imageUrl) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setBranch(branch);
        product.setStock(stock);
        product.setImageUrl(imageUrl);
        product.setDescription("Premium electronic item available in " + branch);
        productService.saveProduct(product);
    }
}
