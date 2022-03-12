package com.example.apelsin_project.entity;

import com.example.apelsin_project.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Detail extends AbsEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @Column(nullable = false)
    private short quantity;
}
