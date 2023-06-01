package com.ipb.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Notification {

  private Long id;
  private String title;
  private String content;
  private Date create_date;
  private boolean is_read;
  private Long store_product_id;
  private Long store_id;
  private String message;


}
