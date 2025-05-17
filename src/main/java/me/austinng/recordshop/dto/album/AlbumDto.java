package me.austinng.recordshop.dto.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.austinng.recordshop.dto.track.TrackDto;

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
    private Double popularity;
    private Boolean isStaffPick;
    private Double discount;
    private ArtistDto artist;
    private List<TrackDto> tracks;
}
