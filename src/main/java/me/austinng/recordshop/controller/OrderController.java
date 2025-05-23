package me.austinng.recordshop.controller;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import me.austinng.recordshop.dto.order.*;
import me.austinng.recordshop.model.*;
import me.austinng.recordshop.repository.AlbumRepository;
import me.austinng.recordshop.repository.CartItemRepository;
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
    CartItemRepository cartItemRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @PostMapping("/pay-for-cart")
    public ResponseEntity<?> payForCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        List<CartItem> cartItems = cartItemRepository.findByUser_Id(user.getId());
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Your cart is empty");
        }

        //create order
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setAlbum(cartItem.getAlbum());
            orderItem.setQuantity(cartItem.getQuantity());
            return orderItem;
        }).collect(Collectors.toList());
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        OrderDto savedOrderDto = orderMapper.toOrderDto(savedOrder);

        //delete all items in cart
        cartItemRepository.deleteByUserId(user.getId());

        return ResponseEntity.ok(savedOrderDto);
    }

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
        OrderDto savedOrderDto = orderMapper.toOrderDto(savedOrder);
        return ResponseEntity.ok(savedOrderDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        long userId = user.getId();
        List<OrderDto> orderDtos = orderRepository.findAllByUserIdOrderByOrderDateDesc(userId)
                .stream()
                .map(order -> orderMapper.toOrderDto(order))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);
    }

}
