package com.project.profile_Service.security;

public class AuthenticatedUser {
    private final Integer userId;
    private final String username;
    private final String role;

    public AuthenticatedUser(Integer userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
