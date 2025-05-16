package me.austinng.recordshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    @Id
    private long id;
    private String title;
    private LocalDate releaseDate;
    private String genre;
    private String label;
    private Double popularity;
    private Boolean isStaffPick;
    private Double discount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private List<Track> tracks;
}
