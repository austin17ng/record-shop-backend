package me.austinng.recordshop.dto.login;

import lombok.Data;

@Data
public class LoginResponse {
    private String username;
    private String token;
}
