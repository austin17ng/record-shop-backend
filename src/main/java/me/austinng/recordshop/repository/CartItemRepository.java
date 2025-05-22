package me.austinng.recordshop.repository;

import me.austinng.recordshop.model.CartItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    @EntityGraph(attributePaths = {"user", "album"})
    List<CartItem> findByUser_Id(long id);
}
