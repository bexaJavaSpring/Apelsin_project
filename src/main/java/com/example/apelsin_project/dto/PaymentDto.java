package com.example.apelsin_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    private BigDecimal amount;
    private UUID invoice_id;
}
