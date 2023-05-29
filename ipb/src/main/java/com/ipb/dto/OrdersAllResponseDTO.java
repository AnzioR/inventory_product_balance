package com.ipb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersAllResponseDTO {
  private Long orders_id;
  private LocalDate orders_date;
  private String orders_status;
}