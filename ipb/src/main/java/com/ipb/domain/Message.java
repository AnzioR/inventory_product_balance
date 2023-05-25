package com.ipb.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Message {
  private String to;
//  누구한테 보내고
  private String content;
//  메세지 보내는 내용
}

