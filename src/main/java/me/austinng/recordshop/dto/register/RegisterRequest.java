package me.austinng.recordshop.dto.register;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterRequest {
    @NotBlank
    @Length(max = 20)
    private String username;

    @NotBlank
    @Length(max = 50)
    private String password;
}
