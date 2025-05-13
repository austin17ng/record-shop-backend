package me.austinng.recordshop.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String username;
    private String token;
}
