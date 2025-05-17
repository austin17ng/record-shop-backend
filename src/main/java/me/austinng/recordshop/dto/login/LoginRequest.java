package me.austinng.recordshop.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequest {
    @NotBlank
    @Length(max = 50)
    private String username;

    @NotBlank
    @Length(max = 50)
    private String password;
}
