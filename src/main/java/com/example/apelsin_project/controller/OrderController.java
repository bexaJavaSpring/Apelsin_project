package com.example.apelsin_project.controller;

import com.example.apelsin_project.dto.ApiResponse;
import com.example.apelsin_project.dto.OrderDto;
import com.example.apelsin_project.entity.Order;
import com.example.apelsin_project.repository.OrderRepository;
import com.example.apelsin_project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    final OrderRepository orderRepository;
    final OrderService orderService;

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id) {
        Optional<Order> byId = orderRepository.findById(id);
        return byId.map(order -> ResponseEntity.ok().body(order)).orElseGet(() -> ResponseEntity.ok().body(new Order()));
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok().body(orderRepository.findAll());
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody OrderDto dto) {
        ApiResponse apiResponse = orderService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable UUID id, @RequestBody OrderDto dto) {
        ApiResponse apiResponse = orderService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        ApiResponse apiResponse = orderService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getOrderProduct(@RequestParam("order_id") UUID id) {
        ApiResponse apiResponse = orderService.getOrderProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
