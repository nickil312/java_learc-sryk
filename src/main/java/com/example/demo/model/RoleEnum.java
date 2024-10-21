package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
//public enum RoleEnum  {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
