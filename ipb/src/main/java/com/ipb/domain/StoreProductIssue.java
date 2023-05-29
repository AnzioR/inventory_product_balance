package com.ipb.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class StoreProductIssue {
    private Long id;
    private Long store_product_id;
    private Integer qnt;
    private Long store_product_desc_id;
    private Date disposal_date;

    private String product_name;
    private int price;
    private int cost;
    private String exp;
    private String category_name;
    private Long store_id;

    public StoreProductIssue(Long id, Long store_product_id, Integer qnt, Long store_product_desc_id, Date disposal_date) {
        this.id = id;
        this.store_product_id = store_product_id;
        this.qnt = qnt;
        this.store_product_desc_id = store_product_desc_id;
        this.disposal_date = disposal_date;
    }

    public StoreProductIssue(Long store_product_id, Integer qnt, Long store_product_desc_id, Date disposal_date) {
        this.store_product_id = store_product_id;
        this.qnt = qnt;
        this.store_product_desc_id = store_product_desc_id;
        this.disposal_date = disposal_date;
    }
}
