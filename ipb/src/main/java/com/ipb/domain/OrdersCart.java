package com.ipb.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrdersCart {
    private Long id;
    private Integer qnt;
    private Long product_id;
    private Long store_id;
}
