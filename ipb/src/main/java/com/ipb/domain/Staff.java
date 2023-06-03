package com.ipb.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
