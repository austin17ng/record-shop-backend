package me.austinng.recordshop.repository;

import me.austinng.recordshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserIdOrderByOrderDateDesc(long userId);
}
