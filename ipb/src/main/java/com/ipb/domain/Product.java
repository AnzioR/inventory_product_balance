package com.ipb.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Product {
    private Long id;
    private String name;
    private int qnt;
    private int price;
    private int cost;
    private Long category_id;
    private int box_qnt;
    private String exp;
    private Long product_code;
    private String category_name;
    private String brand;
    private String detail;
    private String imgname;

    public Product(Long id, String name, int qnt, int price, int cost, Long category_id, int box_qnt, String exp, Long product_code, String brand, String detail, String imgname) {
        this.id = id;
        this.name = name;
        this.qnt = qnt;
        this.price = price;
        this.cost = cost;
        this.category_id = category_id;
        this.box_qnt = box_qnt;
        this.exp = exp;
        this.product_code = product_code;
        this.brand = brand;
        this.detail = detail;
        this.imgname = imgname;

    }

}



