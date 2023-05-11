package com.ipb.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Sales {
    private Long id;
    private int qnt;
    private Long store_product_id;
    private Date sales_date;
    private String product_name;
    private int price;
    private int cost;
    private String exp;
    private Long category_id;
    private String store_name;

    public Sales(Long id, int qnt, Long store_product_id, Date sales_date) {
        this.id = id;
        this.qnt = qnt;
        this.store_product_id = store_product_id;
        this.sales_date = sales_date;
    }
}
