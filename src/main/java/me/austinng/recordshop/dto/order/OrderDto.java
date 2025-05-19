package me.austinng.recordshop.dto.order;

import me.austinng.recordshop.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto (
        long id,
        LocalDateTime orderDate,
        OrderStatus status,
        List<OrderItemDto> orderItems
) {}
