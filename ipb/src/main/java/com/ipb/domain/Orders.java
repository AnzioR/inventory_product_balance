package com.ipb.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Orders {
    private Long id;
    private Integer qnt;
    private Long product_id;
    private Long store_id;
    private Long delivery_id;
    private Date orders_date;

    private String store_name;
    private String product_name;
    private int orders_qnt;
    private int product_box_qnt;
    private String orders_status;
    private int product_inventory_qnt;
    private String product_exp;


    public Orders(Long id, Integer qnt, Long product_id, Long store_id, Long delivery_id, Date orders_date) {
        this.id = id;
        this.qnt = qnt;
        this.product_id = product_id;
        this.store_id = store_id;
        this.delivery_id = delivery_id;
        this.orders_date = orders_date;
    }

    public Orders(Integer qnt, Long product_id, Long store_id, Long delivery_id) {
        this.qnt = qnt;
        this.product_id = product_id;
        this.store_id = store_id;
        this.delivery_id = delivery_id;
    }
}
