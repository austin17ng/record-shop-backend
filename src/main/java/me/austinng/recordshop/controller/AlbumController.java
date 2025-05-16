package me.austinng.recordshop.controller;

import lombok.extern.slf4j.Slf4j;
import me.austinng.recordshop.dto.AlbumDto;
import me.austinng.recordshop.dto.AlbumMapper;
import me.austinng.recordshop.model.Album;
import me.austinng.recordshop.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumMapper albumMapper;

    @GetMapping
    public List<AlbumDto> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        return albums.stream().map(albumMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/new-arrivals")
    public List<AlbumDto> getAllNewArrivals() {
        List<Album> albums = albumRepository.findTop30ByOrderByReleaseDateDesc();
        return albums.stream().map(albumMapper::toDto)
                .collect(Collectors.toList());
    }
}
