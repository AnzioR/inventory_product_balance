package com.ipb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreAutoOrders {
  private Long id;
  private int qnt;
  private int min_qnt;
  private Long store_product_id;
  private Long product_id;
  private Long product_code;

  private String product_name;

  private int product_price;
  private int product_cost;

  private Long store_id;
  private String brand;
}
