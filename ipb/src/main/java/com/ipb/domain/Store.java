package com.ipb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
  private Long id;
  private String name;
  private String location;
  private String number;
  private String img;
}
