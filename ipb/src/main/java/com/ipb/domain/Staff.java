package com.ipb.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Staff {
    private Long id;
    private String name;
    private String login_id;
    private String pwd;
    private Long store_id;
}
