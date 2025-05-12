package me.austinng.recordshop.dto;

import lombok.Data;

@Data
public class ArtistDto {
    private Long id;
    private String name;
    private String country;
    private String genre;
}
