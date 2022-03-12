package com.example.apelsin_project.repository;

import com.example.apelsin_project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

    boolean existsByNameIgnoreCase(String name);
}
