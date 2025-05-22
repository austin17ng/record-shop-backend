package me.austinng.recordshop.controller;

import lombok.extern.slf4j.Slf4j;
import me.austinng.recordshop.dto.album.AlbumDto;
import me.austinng.recordshop.dto.album.AlbumMapper;
import me.austinng.recordshop.dto.cart.AddToCartRequest;
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

@Slf4j
@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public List<CartItem> getCarts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return cartItemRepository.findByUser_Id(user.getId());
    }

    @PostMapping("/add-item")
    public void addAlbum(@RequestBody AddToCartRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Album album = albumRepository.findById(request.albumId()).orElseThrow(() -> new RuntimeException("Album not found"));
        CartItem cartItem = new CartItem();
        cartItem.setAlbum(album);
        cartItem.setUser(user);

        cartItemRepository.save(cartItem);
    }
}
