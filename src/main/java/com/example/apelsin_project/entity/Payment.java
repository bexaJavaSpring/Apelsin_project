package com.example.apelsin_project.entity;

import com.example.apelsin_project.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Timestamp;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment extends AbsEntity {

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;
}
