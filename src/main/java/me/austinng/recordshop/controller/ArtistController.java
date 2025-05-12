package me.austinng.recordshop.controller;

import me.austinng.recordshop.model.Artist;
import me.austinng.recordshop.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @PostMapping
    public Artist addArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable Long id) {
        return artistRepository.findById(id).orElseThrow(() -> new RuntimeException("Artist not found"));
    }

    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable Long id, @RequestBody Artist updatedArtist) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new RuntimeException("Artist not found"));
        artist.setName(updatedArtist.getName());
        artist.setCountry(updatedArtist.getCountry());
        artist.setGenre(updatedArtist.getGenre());
        return artistRepository.save(artist);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id) {
        artistRepository.deleteById(id);
    }

}
