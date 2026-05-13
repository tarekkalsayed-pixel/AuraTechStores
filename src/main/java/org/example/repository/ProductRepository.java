package org.example.repository;
//db access layer ll prods
import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Fetch products
//Filter by branch
//Save/delete products
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBranch(String branch);
}
