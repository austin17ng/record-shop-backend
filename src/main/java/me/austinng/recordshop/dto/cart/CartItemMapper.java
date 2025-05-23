package me.austinng.recordshop.dto.cart;

import me.austinng.recordshop.dto.album.AlbumMapper;
import me.austinng.recordshop.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AlbumMapper.class})
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);
    CartItem toEnEntity(CartItemDto cartItemDto);
}
