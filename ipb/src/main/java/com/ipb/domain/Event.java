package com.ipb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
