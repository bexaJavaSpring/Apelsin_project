package com.example.apelsin_project.service;

import com.example.apelsin_project.dto.ApiResponse;
import com.example.apelsin_project.dto.ProductDto;
import com.example.apelsin_project.entity.Category;
import com.example.apelsin_project.entity.Product;
import com.example.apelsin_project.repository.CategoryRepository;
import com.example.apelsin_project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;

    public ApiResponse add(ProductDto dto) {
        Optional<Category> byId = categoryRepository.findById(dto.getCategoryId());
        if (!byId.isPresent()) {
            return new ApiResponse("Xatolik", false);
        }
        if (productRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("Bunaqa name lik product bor", false);
        }
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(BigDecimal.valueOf(dto.getPrice()));
        product.setPhoto(dto.getPhotoURL());
        product.setCategory(byId.get());
        Product save = productRepository.save(product);
        return new ApiResponse("Added", true, save);
    }

    public ApiResponse edit(Integer id, ProductDto dto) {
        Optional<Category> byId = categoryRepository.findById(dto.getCategoryId());
        if (!byId.isPresent()) {
            return new ApiResponse("Xatolik", false);
        }
        if (productRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("Bunaqa name lik product bor", false);
        }
        Optional<Product> byId1 = productRepository.findById(id);
        Product product = byId1.get();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPhoto(dto.getPhotoURL());
        product.setPrice(BigDecimal.valueOf(dto.getPrice()));
        product.setCategory(byId.get());
        Product save = productRepository.save(product);
        return new ApiResponse("Edit!", true, save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        Product product = byId.get();
        product.setActive(false);
        productRepository.save(product);
        return new ApiResponse("Deleted", true);
    }
}
