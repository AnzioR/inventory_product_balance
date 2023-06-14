package com.ipb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff  {
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
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return pwd;
//    }
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Override
//    public String getUsername() {
//        return String.valueOf(this.id);
//    }
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
