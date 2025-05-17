package me.austinng.recordshop.dto.order;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto (
        long id,
        LocalDateTime orderDate,
        List<OrderItemDto> orderItems
) {}
