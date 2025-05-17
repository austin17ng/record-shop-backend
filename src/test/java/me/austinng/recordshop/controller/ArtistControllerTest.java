package me.austinng.recordshop.controller;

import me.austinng.recordshop.dto.album.ArtistDto;
import me.austinng.recordshop.dto.album.ArtistMapper;
import me.austinng.recordshop.model.Artist;
import me.austinng.recordshop.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArtistControllerTest {
    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private ArtistMapper artistMapper;

    @InjectMocks
    private ArtistController artistController;

    @Test
    public void getAllArtists_returnAllArtists() {
        List<Artist> artists = List.of(new Artist(0L, "Central Cee", "UK", "Rap"));
        when(artistRepository.findAll()).thenReturn(artists);

        List<ArtistDto> result = artistController.getAllArtists();
        assertEquals(1, result.size());
    }
}
