package com.example.apelsin_project.entity;

import com.example.apelsin_project.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Invoice extends AbsEntity {
    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    @Column(nullable = false)
    private BigDecimal amount;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(nullable = false)
    private Date issued;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(nullable = false)
    private java.util.Date due;
}
