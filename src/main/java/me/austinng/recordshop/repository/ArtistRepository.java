package me.austinng.recordshop.repository;

import jakarta.persistence.Entity;
import me.austinng.recordshop.model.Album;
import me.austinng.recordshop.model.Artist;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
