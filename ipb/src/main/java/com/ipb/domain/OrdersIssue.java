package com.ipb.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrdersIssue {
    private Long id;
    private Long orders_id;
    private Integer qnt;
    private Long order_desc_id;
    private Date disposal_date;
}
