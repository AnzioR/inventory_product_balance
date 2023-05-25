package com.ipb.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SmsResponse {
  private String requestId;
  private LocalDateTime requestTime;
  private String statusCode;
  private String statusName;
}
