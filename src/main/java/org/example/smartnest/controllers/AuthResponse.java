package org.example.smartnest.controllers;

public class AuthResponse {

    private String token;
    private String username;

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public AuthResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AuthResponse setUsername(String username) {
        this.username = username;
        return this;
    }
}
