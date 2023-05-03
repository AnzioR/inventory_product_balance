package com.ipb.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Board {

  private Long id;
  private String title;
  private String body_text;
  private Long staff_id;
  private String imgname;
  private Date write_date;

}
