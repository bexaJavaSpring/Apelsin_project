package com.example.apelsin_project.repository;

import com.example.apelsin_project.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    Optional<Invoice> findByOrderId(UUID id);
}
