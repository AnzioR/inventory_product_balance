package com.ipb.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class SmsRequest {
  private String type;
  private String contentType;
  private String countryCode;
  private String from;
  private String content;
  private List<Message> messages;  //messages 객체를 여러개 담고 있는거 to,content
}
