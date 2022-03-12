package com.example.apelsin_project.controller;

import com.example.apelsin_project.dto.ApiResponse;
import com.example.apelsin_project.entity.Category;
import com.example.apelsin_project.repository.CategoryRepository;
import com.example.apelsin_project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    final CategoryRepository categoryRepository;
    final CategoryService categoryService;

    @PreAuthorize("hasAnyAuthority('READ_ONE')")
//@PreAuthorize("hasAnyAuthority(Permission.READ_ONE)")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.map(category -> ResponseEntity.ok().body(category)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Category()));
    }

    //   @PreAuthorize("hasAnyAuthority(Permission.READ_ALL)")
    @PreAuthorize("hasAnyAuthority('READ_ALL')")
    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }

    @PreAuthorize("hasAnyAuthority(Permission.CREATE)")
    @PostMapping
    public HttpEntity<?> add(@RequestBody Category category) {
        ApiResponse apiResponse = categoryService.add(category);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority(Permission.PUT)")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody Category category) {
        ApiResponse apiResponse = categoryService.edit(id, category);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getCategoryProduct(@RequestParam("product_id") Integer id) {
        ApiResponse apiResponse = categoryService.getCategoryProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 404).body(apiResponse);
    }
}
