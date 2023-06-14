package com.ipb.domain;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class StoreProduct {
    private Long id;
    private Integer qnt;
    private Long product_id;
    private Long store_id;
    private boolean is_using;
    private String product_name;
    private Integer price;
    private Integer box_qnt;
    private String exp;
    private String brand;
    private String category_name;
    private String detail;
    private Integer store_price;
    private Double event_rate;
    private LocalDate expirationDate;
    private Integer safe_qnt;
    private int cost;
    private String storage;
    private boolean is_auto;
    private Long product_code;
    private String store_number;
    private String imgname;
    private Integer sp_qnt;


    public StoreProduct(Long product_id, Long store_id) {
        this.product_id = product_id;
        this.store_id = store_id;
    }

    public StoreProduct(Long id, Integer qnt, Long product_id, Long store_id) {
        this.id = id;
        this.qnt = qnt;
        this.product_id = product_id;
        this.store_id = store_id;
    }

    public StoreProduct(Integer qnt, Long product_id, Long store_id, boolean is_using) {
        this.qnt = qnt;
        this.product_id = product_id;
        this.store_id = store_id;
        this.is_using = is_using;
    }

    public StoreProduct(Long id, Integer qnt, Long product_id, Long store_id, boolean is_using,Integer store_price,Double event_rate) {
        this.id = id;
        this.qnt = qnt;
        this.product_id = product_id;
        this.store_id = store_id;
        this.is_using = is_using;
        this.store_price = store_price;
        this.event_rate = event_rate;

    }

    public StoreProduct(Integer qnt, Long product_id, Long store_id, boolean is_using, int store_price, double event_rate) {
        this.qnt = qnt;
        this.product_id = product_id;
        this.store_id = store_id;
        this.is_using = is_using;
        this.store_price = store_price;
        this.event_rate = event_rate;
    }

    public StoreProduct(Long id, Integer qnt, Long product_id, Long store_id, boolean is_using, Integer store_price, Double event_rate, boolean is_auto, Long product_code) {
        this.id = id;
        this.qnt = qnt;
        this.product_id = product_id;
        this.store_id = store_id;
        this.is_using = is_using;
        this.store_price = store_price;
        this.event_rate = event_rate;
        this.is_auto = is_auto;
        this.product_code = product_code;
    }

    public void changeQuantity(Integer newQuantity) {
        this.qnt = newQuantity;
    }

    public void product_name() {
        this.product_name = product_name;
    }


}

