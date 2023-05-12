package com.ipb.domain;

import lombok.*;

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

    public void changeQuantity(Integer newQuantity) {
        this.qnt = newQuantity;
    }
}

