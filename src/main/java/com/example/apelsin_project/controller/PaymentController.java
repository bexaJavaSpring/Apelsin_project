package com.example.apelsin_project.controller;

import com.example.apelsin_project.dto.ApiResponse;
import com.example.apelsin_project.dto.PaymentDto;
import com.example.apelsin_project.entity.Payment;
import com.example.apelsin_project.repository.PaymentRepository;
import com.example.apelsin_project.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    final PaymentRepository paymentRepository;
    final PaymentService paymentService;

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id) {
        Optional<Payment> byId = paymentRepository.findById(id);
        return byId.map(payment -> ResponseEntity.ok().body(payment)).orElseGet(() -> ResponseEntity.ok().body(new Payment()));
    }

    @GetMapping("/list")
    public HttpEntity<?> getList() {
        return ResponseEntity.ok().body(paymentRepository.findAll());
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody PaymentDto dto) {
        ApiResponse apiResponse = paymentService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/details")
    public HttpEntity<?> getDetails(@RequestParam("payment_details_id") UUID id) {
        Optional<Payment> byId = paymentRepository.findById(id);
        return byId.map(payment -> ResponseEntity.ok().body(payment)).orElseGet(() -> ResponseEntity.ok().body(new Payment()));
    }
}
