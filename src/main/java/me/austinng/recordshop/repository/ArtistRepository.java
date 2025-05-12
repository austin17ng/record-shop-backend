package me.austinng.recordshop.repository;

import me.austinng.recordshop.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
