package com.example.apelsin_project.dto;

import com.example.apelsin_project.entity.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
  private Integer custId;
  private List<DetailDto> detailDtoList;
}
