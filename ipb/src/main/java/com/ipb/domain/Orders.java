package com.ipb.domain;

import lombok.*;

import java.sql.Date;

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
    private Long orders_type_id;

    private String store_name;
    private String product_name;
    private int orders_qnt;
    private int product_info_box_qnt;
    private String orders_status;
    private int product_inventory_qnt;
    private String product_exp;

    private Long product_info_id;
    private int product_qnt;
    private int product_price;

    private int product_cost;

    private Long product_code;
    private String product_info_brand;
    private String product_info_detail;
    private String product_info_imgname;
    private String product_info_storage;


    public Orders(Long store_id, Date orders_date){
        this.store_id = store_id;
        this.orders_date = orders_date;
    }
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
    public Orders(Integer qnt, Long product_id, Long store_id, Long delivery_id, Long orders_type_id) {
        this.qnt = qnt;
        this.product_id = product_id;
        this.store_id = store_id;
        this.delivery_id = delivery_id;
        this.orders_type_id = orders_type_id;
    }
}
