package com.ipb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersAllResponseDTOList {
  private List<OrdersAllResponseDTO> ordersAllResponseDTOList;
}
