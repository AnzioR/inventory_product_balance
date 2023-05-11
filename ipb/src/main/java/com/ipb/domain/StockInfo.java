package com.ipb.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StockInfo {
  private Long id;
  private String detail;
  private String product_name;
  private int product_price;
  private int qnt;
  private int box_qnt;
  private boolean is_using;
  private String exp;
  private String brand;
  private String category_name;
  private String imgname;
}



