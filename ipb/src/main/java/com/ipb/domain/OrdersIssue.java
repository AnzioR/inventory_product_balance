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
    private Long orders_desc_id;
    private Date disposal_date;
    private String orders_issue;
    private String  product_name;
    private int price;
    private String category_name;
    private Long store_id;



    public OrdersIssue(Long id, Long orders_id, Integer qnt, Long orders_desc_id, Date disposal_date) {
        this.id = id;
        this.orders_id = orders_id;
        this.qnt = qnt;
        this.orders_desc_id = orders_desc_id;
        this.disposal_date = disposal_date;
    }
}
