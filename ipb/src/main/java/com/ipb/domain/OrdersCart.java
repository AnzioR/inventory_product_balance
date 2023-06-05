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

    private Long product_code;
    private String name;
    private String brand;
    private String detail;
    private String imgname;
    private Long category_id;
    private String category_name;
    private int box_qnt;

    public OrdersCart(Long id, Integer qnt, Long product_id, Long store_id) {
        this.id = id;
        this.qnt = qnt;
        this.product_id = product_id;
        this.store_id = store_id;
    }

    public OrdersCart(Integer qnt, Long product_id, Long store_id) {
        this.qnt = qnt;
        this.product_id = product_id;
        this.store_id = store_id;
    }
}
