package me.austinng.recordshop.dto.order;

import me.austinng.recordshop.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {
    OrderDto toOrderDto(Order order);
}
