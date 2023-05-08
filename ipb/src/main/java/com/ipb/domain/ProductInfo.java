package com.ipb.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductInfo {
    private Long product_code;
    private String name;
    private String brand;
    private String detail;
    private String imgname;
    private Long category_id;
    private String category_name;
    private int box_qnt;

  public ProductInfo(Long product_code, String name, String brand, String detail, String imgname, Long category_id, int box_qnt) {
    this.product_code = product_code;
    this.name = name;
    this.brand = brand;
    this.detail = detail;
    this.imgname = imgname;
    this.category_id = category_id;
    this.box_qnt = box_qnt;
  }
}



