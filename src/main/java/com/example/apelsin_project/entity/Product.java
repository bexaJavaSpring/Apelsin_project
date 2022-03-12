package com.example.apelsin_project.entity;

import com.example.apelsin_project.entity.templete.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product extends AbsNameEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(length = 20)
    private String description;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String photo;
}
