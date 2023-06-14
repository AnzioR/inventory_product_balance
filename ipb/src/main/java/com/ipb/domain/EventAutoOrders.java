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
    private String product_name;
    private Long product_id;
    private Long product_info_id;
    private Integer cost;
    private Integer price;
    private String name;
    private Long event_id;
    private String start_date;
    private String end_date;
    private String brand;


    public EventAutoOrders(Long event_product_id) {
        this.event_product_id = event_product_id;
    }

    public EventAutoOrders(Long id, Integer qnt) {
        this.id = id;
        this.qnt = qnt;
    }

    public EventAutoOrders(Long id, Integer qnt, Long event_product_id, Long store_id) {
        this.id = id;
        this.qnt = qnt;
        this.event_product_id = event_product_id;
        this.store_id = store_id;
    }
}
