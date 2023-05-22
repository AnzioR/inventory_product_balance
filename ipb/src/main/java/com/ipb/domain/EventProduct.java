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

  private String product_name;
  private String product_imgname;
  private String event_imgname;
  private int qnt;
  private int price;
  private String start_date;
  private String end_date;


  public EventProduct(Long id, Long product_id, Long event_id) {
    this.id = id;
    this.product_id = product_id;
    this.event_id = event_id;
  }

}
