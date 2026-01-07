package com.java.userservice.module;

public enum RoleBasedAuthority {

    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private String role;

    public String getRole() {
        return role;
    }

    private RoleBasedAuthority(String role) {
        this.role = role;
    }



}
