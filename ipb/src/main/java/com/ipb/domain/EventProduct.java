package com.ipb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventProduct {
  private Long id;
  private Long product_id;
  private Long event_id;

  private String name;
  private String imgname;
  private int qnt;
  private int price;
  private String start_date;
  private String end_date;
  private Long product_code;

  private String brand;
  private String detail;

  private Long category_id;
  private String category_name;
  private int box_qnt;

  private String storage;


  public EventProduct(Long id, Long product_id, Long event_id) {
    this.id = id;
    this.product_id = product_id;
    this.event_id = event_id;
  }

}
