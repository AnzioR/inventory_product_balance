package com.ipb.domain;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Product {
    private Long id;
    private Long product_info_id;
    private int qnt;
    private int price;
    private int cost;
    private Long category_id;
    private String exp;
    private String category_name;
    private Long product_code;
    private String name;
    private String imgname;
    private String detail;
    private String storage;
    private String brand;
    private String store_qnt;
    private Integer safe_qnt;
    private Integer total_qnt;
    private Integer all_qnt;

    public Product(Long id, int qnt, int price, int cost, String exp, Long product_info_id) {
        this.id = id;
        this.qnt = qnt;
        this.price = price;
        this.cost = cost;
        this.exp = exp;
        this.product_info_id = product_info_id;
    }
}



