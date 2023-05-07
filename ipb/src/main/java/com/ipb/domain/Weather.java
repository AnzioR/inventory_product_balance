package com.ipb.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Weather {
    private Long id;
    private Long store_id;
    private String status;
    private Double temp;
}
