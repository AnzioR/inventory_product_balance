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
    private String brand;
    private String detail;
    private String imgname;
}
