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
  private String name;
  private String imgname;
  private Date write_date;

  public Board(Long id, String title, String body_text, Long staff_id, String imgname, Date write_date) {
    this.id = id;
    this.title = title;
    this.body_text = body_text;
    this.staff_id = staff_id;
    this.imgname = imgname;
    this.write_date = write_date;
  }
}
