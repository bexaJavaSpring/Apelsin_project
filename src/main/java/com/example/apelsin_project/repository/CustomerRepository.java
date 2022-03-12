package com.example.apelsin_project.repository;

import com.example.apelsin_project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    boolean existsByNameIgnoreCase(String name);
}
