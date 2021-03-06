package com.example.apelsin_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private String name;
    private Integer categoryId;
    private String description;
    private Double price;
    private String photoURL;
}
