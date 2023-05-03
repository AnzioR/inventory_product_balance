package com.ipb.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SalesIssue {
    private Long id;
    private Long store_product_id;
    private Integer qnt;
    private Long store_desc_id;
    private Date disposal_date;

}
