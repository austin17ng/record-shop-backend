package me.austinng.recordshop.repository;

import jakarta.transaction.Transactional;
import me.austinng.recordshop.model.CartItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    @EntityGraph(attributePaths = {"user", "album"})
    List<CartItem> findByUser_Id(long id);

    Optional<CartItem> findByUserIdAndAlbumId(Long useId, Long albumId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cart_item SET quantity = quantity + 1 WHERE user_id = :userId AND album_id = :albumId", nativeQuery = true)
    int incrementQuantityByUserIdAndAlbumId(@Param("userId") Long userId, @Param("albumId") Long albumId);


}
