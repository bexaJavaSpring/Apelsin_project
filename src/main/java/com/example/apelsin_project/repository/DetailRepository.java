package com.example.apelsin_project.repository;

import com.example.apelsin_project.entity.Detail;
import com.example.apelsin_project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DetailRepository extends JpaRepository<Detail, UUID> {

   List<Detail> findAllByProduct_Id(Integer id);

   List<Detail> findAllByOrderId(UUID id);
}
