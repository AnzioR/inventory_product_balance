package com.ipb.domain;

import lombok.*;

@Data
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private Long id;
    private String name;
    private String login_id;
    private String pwd;
    private Long store_id;
    private String store_name;
    private String area;


    public Staff(Long id, String name, String login_id, String pwd, Long store_id) {
        this.id = id;
        this.name = name;
        this.login_id = login_id;
        this.pwd = pwd;
        this.store_id = store_id;
    }
}
