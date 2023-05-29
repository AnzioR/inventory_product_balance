package com.ipb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
  private Long orders_id;
  private String product_code;
  private String product_name;
  private int product_qnt;
  private int product_price;
  private LocalDate product_exp; // 2023-05-27
}
