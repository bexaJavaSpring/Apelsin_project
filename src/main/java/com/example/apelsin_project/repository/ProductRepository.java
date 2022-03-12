package com.example.apelsin_project.repository;

import com.example.apelsin_project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    boolean existsByNameIgnoreCase(String name);
}
