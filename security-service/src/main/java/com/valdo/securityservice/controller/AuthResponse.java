package com.valdo.securityservice.controller;

/**
 * DTO para representar la respuesta de autenticación (token JWT).
 */
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    // Getters y setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
