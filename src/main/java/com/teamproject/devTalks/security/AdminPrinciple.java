package com.teamproject.devTalks.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.teamproject.devTalks.entity.user.AdminEntity;

public class AdminPrinciple implements UserDetails {

    private AdminEntity adminEntity;

    public AdminPrinciple(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return authorities;
    }
    
    public String getAdminEmail() {
        return adminEntity.getAdminEmail();
    }

    @Override
    public String getPassword() {
        return adminEntity.getAdminPassword();
    }

    @Override
    public String getUsername() {
        return adminEntity.getAdminName();
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
