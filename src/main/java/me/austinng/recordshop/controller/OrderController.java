package me.austinng.recordshop.controller;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import me.austinng.recordshop.dto.order.*;
import me.austinng.recordshop.model.Album;
import me.austinng.recordshop.model.Order;
import me.austinng.recordshop.model.OrderItem;
import me.austinng.recordshop.model.User;
import me.austinng.recordshop.repository.AlbumRepository;
import me.austinng.recordshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());

        orderRequest.items().forEach(orderItemRequest -> {
            long albumId = orderItemRequest.albumId();
            int quantity = orderItemRequest.quantity();
            Album album = albumRepository.findById(albumId).orElseThrow(() -> new RuntimeException("Album not found: " + albumId));

            OrderItem orderItem = new OrderItem();
            orderItem.setAlbum(album);
            orderItem.setQuantity(quantity);

            order.addItem(orderItem);
        });

        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(orderMapper.toOrderDto(savedOrder));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        long userId = user.getId();
        List<OrderDto> orders = orderRepository.findAllByUserIdOrderByOrderDateDesc(userId)
                .stream()
                .map(order -> orderMapper.toOrderDto(order))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

}
