package com.ipb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
  private Long id;
  private String name;
  private String location;
  private String number;
  private String imgname;
  private String area;
  private Integer lat;
  private Integer lon;

}