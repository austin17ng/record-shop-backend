package me.austinng.recordshop.repository;

import me.austinng.recordshop.model.Album;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @EntityGraph(attributePaths = {"artist", "tracks"})
    List<Album> findAll();

    @EntityGraph(attributePaths = {"artist", "tracks"})
    Optional<Album> findById(long id);

    @EntityGraph(attributePaths = {"artist", "tracks"})
    List<Album> findTop30ByOrderByReleaseDateDesc();

    //TODO
    @EntityGraph(attributePaths = {"artist", "tracks"})
    List<Album> findTop30ByOrderByPopularityDesc();

    @EntityGraph(attributePaths = {"artist", "tracks"})
    List<Album> findTop30ByIsStaffPickTrueOrderByPopularityDesc();

    @EntityGraph(attributePaths = {"artist", "tracks"})
    List<Album> findTop30ByOrderByDiscountDesc();
}
