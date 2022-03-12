package com.example.apelsin_project.controller;

import com.example.apelsin_project.dto.ApiResponse;
import com.example.apelsin_project.dto.CustomerDto;
import com.example.apelsin_project.entity.Customer;
import com.example.apelsin_project.repository.CategoryRepository;
import com.example.apelsin_project.repository.CustomerRepository;
import com.example.apelsin_project.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    final CustomerRepository customerRepository;
    final CustomerService customerService;

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        return byId.map(customer -> ResponseEntity.ok().body(customer)).orElseGet(() -> ResponseEntity.ok().body(new Customer()));
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok().body(customerRepository.findAll());
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody CustomerDto dto) {
        ApiResponse apiResponse = customerService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CustomerDto dto) {
        ApiResponse apiResponse = customerService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = customerService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
