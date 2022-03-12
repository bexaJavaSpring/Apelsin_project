package com.example.apelsin_project.entity;

import com.example.apelsin_project.entity.templete.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends AbsNameEntity {
}
