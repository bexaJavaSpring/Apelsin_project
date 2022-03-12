package com.example.apelsin_project.repository;

import com.example.apelsin_project.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeteailReposirory extends JpaRepository<Detail, UUID> {
}
