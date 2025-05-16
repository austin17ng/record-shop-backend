package me.austinng.recordshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArtistDto {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    private String country;
    private String genre;
}
