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
    Optional<CartItem> findByUserIdAndAlbumId(Long userId, Long albumId);

    @EntityGraph(attributePaths = {"album", "user"})
    List<CartItem> findByUser_Id(Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cart_item SET quantity = quantity + 1 WHERE user_id = :userId AND album_id = :albumId", nativeQuery = true)
    int incrementQuantityByUserIdAndAlbumId(@Param("userId") Long userId, @Param("albumId") Long albumId);


    void deleteByUserId(Long userId);
}
