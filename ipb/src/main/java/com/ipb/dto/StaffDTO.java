package com.ipb.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class StaffDTO {
    private Long id;
    private String name;
    private String login_id;
    private String pwd;
    private Long store_id;
}
