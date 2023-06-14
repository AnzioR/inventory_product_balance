package com.ipb.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Notification {

  private Long id;
  private Integer qnt;
  private Long product_id;
  private Long store_id;
  private boolean is_using;
  private String product_name;
  private Integer safe_qnt;
  private boolean is_auto;
  private Long product_code;

}
