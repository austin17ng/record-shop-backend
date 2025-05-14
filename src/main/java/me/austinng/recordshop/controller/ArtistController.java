package me.austinng.recordshop.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import me.austinng.recordshop.dto.ArtistDto;
import me.austinng.recordshop.dto.ArtistMapper;
import me.austinng.recordshop.exception.ResourceNotFoundException;
import me.austinng.recordshop.model.Artist;
import me.austinng.recordshop.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistMapper artistMapper;

    @PostMapping
    public ArtistDto addArtist(@RequestBody @Valid ArtistDto artistDto) {
        log.info("Add artist: {}", artistDto);
        Artist artist = artistMapper.toEntity(artistDto);
        Artist savedArtist = artistRepository.save(artist);
        return artistMapper.toArtistDto(savedArtist);
    }

    @GetMapping
    public List<ArtistDto> getAllArtists() {
        log.info("Get all artists");
        return artistRepository.findAll().stream()
                .map(artistMapper::toArtistDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ArtistDto getArtistById(@PathVariable Long id) {
        return artistRepository.findById(id)
                .map(artistMapper::toArtistDto)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
    }

    @PutMapping("/{id}")
    public ArtistDto updateArtist(@PathVariable Long id, @RequestBody ArtistDto updatedArtistDto) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
        artist.setName(updatedArtistDto.getName());
        artist.setCountry(updatedArtistDto.getCountry());
        artist.setGenre(updatedArtistDto.getGenre());
        Artist savedArtist = artistRepository.save(artist);
        return artistMapper.toArtistDto(savedArtist);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id) {
        artistRepository.deleteById(id);
    }

}
