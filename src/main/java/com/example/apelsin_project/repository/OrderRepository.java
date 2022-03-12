package com.example.apelsin_project.repository;

import com.example.apelsin_project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {


}
