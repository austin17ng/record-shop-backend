package me.austinng.recordshop.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import me.austinng.recordshop.dto.cart.AddItemRequest;
import me.austinng.recordshop.dto.cart.AddSingleItemRequest;
import me.austinng.recordshop.dto.cart.CartItemDto;
import me.austinng.recordshop.dto.cart.CartItemMapper;
import me.austinng.recordshop.model.Album;
import me.austinng.recordshop.model.CartItem;
import me.austinng.recordshop.model.User;
import me.austinng.recordshop.repository.AlbumRepository;
import me.austinng.recordshop.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    CartItemMapper cartItemMapper;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public List<CartItemDto> getCarts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        List<CartItem> cartItems = cartItemRepository.findByUser_Id(user.getId());

        return cartItems.stream().map(cartItemMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping("/add-item")
    public void addItem(@RequestBody @Valid AddItemRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Album album = albumRepository.findById(request.albumId()).orElseThrow(() -> new RuntimeException("Album not found"));
        CartItem cartItem = new CartItem();
        cartItem.setAlbum(album);
        cartItem.setUser(user);
        cartItem.setQuantity(request.quantity());

        cartItemRepository.save(cartItem);
    }

    @PostMapping("/add-single-item")
    public void addSingleItem(@RequestBody @Valid AddSingleItemRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Album album = albumRepository.findById(request.albumId()).orElseThrow(() -> new RuntimeException("Album not found"));

        Optional<CartItem> cartItem = cartItemRepository.findByUserIdAndAlbumId(user.getId(), request.albumId());

        if (cartItem.isPresent()) {
            cartItemRepository.incrementQuantityByUserIdAndAlbumId(user.getId(), request.albumId());
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setUser(user);
            newCartItem.setAlbum(album);
            newCartItem.setQuantity(1);
            cartItemRepository.save(newCartItem);
        }
    }
}
