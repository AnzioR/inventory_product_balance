package com.ipb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
  private Long id;
  private String name;
  private Long event_type_id;
  private String imgname;
  private String start_date;
  private String end_date;

  private String event_name;
  private String type_name;
 private  String category_name;

  public Event(Long id, String name, Long event_type_id, String imgname, String start_date, String end_date) {
    this.id = id;
    this.name = name;
    this.event_type_id = event_type_id;
    this.imgname = imgname;
    this.start_date = start_date;
    this.end_date = end_date;
  }
}
