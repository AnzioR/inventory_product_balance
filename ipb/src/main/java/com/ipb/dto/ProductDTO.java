package com.ipb.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductDTO {
    private Long id;
    private String name;
    private int qnt;
    private int price;
    private int cost;
    private Long category_id;
    private int box_qnt;
    private Date exp;
    private Long product_code;
    private Long brand;
    private String detail;
    private String imgname;
}
