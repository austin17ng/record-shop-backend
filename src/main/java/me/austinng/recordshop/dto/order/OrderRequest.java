package me.austinng.recordshop.dto.order;

import java.util.List;

public record OrderRequest(
        List<OrderItemRequest> items
) {}
