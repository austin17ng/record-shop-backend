package me.austinng.recordshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlbumDto {
    private long id;
    private String title;
    private String releaseDate;
    private String genre;
    private String label;
    private ArtistDto artist;
    private List<TrackDto> tracks;
}
