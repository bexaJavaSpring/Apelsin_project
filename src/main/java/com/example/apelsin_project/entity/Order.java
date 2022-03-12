package com.example.apelsin_project.entity;

import com.example.apelsin_project.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "orders")
public class Order extends AbsEntity {

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
