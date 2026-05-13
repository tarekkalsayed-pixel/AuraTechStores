package org.example.service;
//middle layer bet el controller wel db
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByBranch(String branch) {
        return productRepository.findByBranch(branch);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(new Product());
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
