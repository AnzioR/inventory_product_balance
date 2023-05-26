package com.ipb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreAutoOrders {
  private Long store_auto_orders_id;
  private int qnt;
  private int min_qnt;
  private Long store_product_id;
  private Long product_id;
  private Long product_code;

  private Long store_id;
}
