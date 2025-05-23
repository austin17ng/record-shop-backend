package me.austinng.recordshop.dto.cart;

import me.austinng.recordshop.dto.album.AlbumDto;
import me.austinng.recordshop.model.Album;

public record CartItemDto(
        long id,
        AlbumDto album,
        int quantity
) {
}
