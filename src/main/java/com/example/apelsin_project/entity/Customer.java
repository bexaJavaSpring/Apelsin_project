package com.example.apelsin_project.entity;

import com.example.apelsin_project.entity.templete.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer extends AbsNameEntity {
    @Length(max = 3)
    @Column(nullable = false)
    private String country;
    private String address;
    @Length(max = 50)
    private String phone;
}
