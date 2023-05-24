package com.ipb.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class EventAutoOrders {
    private Long id;
    private Integer qnt;
    private Long event_product_id;
    private Long store_id;
}
