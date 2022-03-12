package com.example.apelsin_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private String name;
    private String country;
    private String address;
    private String phone;
}
