package com.company.medical.model;

import com.company.medical.domain.Account;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class AccountModel extends Account implements UserDetails {
    private String urealName;//用户真实姓名
    private Collection<? extends GrantedAuthority> authorities;//认证

    public AccountModel() {}

    public AccountModel(Long id, String uname,String realname, String role, String pwd, Collection<? extends GrantedAuthority> authorities) {
        this.setId(id);
        this.setUname(uname);
        this.setUtype(role);
        this.setUrealName(realname);
        this.setPwd(pwd);
        this.authorities = authorities;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }

    @Override
    public String getPassword() {
        return this.getPwd();
    }

    @Override
    public String getUsername() {
        return this.getUname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}