package com.example.apelsin_project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessage {
    private String name;
    private LocalDate time;
    private String description;
}
