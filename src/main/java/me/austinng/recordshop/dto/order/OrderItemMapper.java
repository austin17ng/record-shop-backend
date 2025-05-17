package me.austinng.recordshop.dto.order;

import me.austinng.recordshop.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AlbumSummaryMapper.class)
public interface OrderItemMapper {
    OrderItemDto toOrderItemDto(OrderItem orderItem);
}
