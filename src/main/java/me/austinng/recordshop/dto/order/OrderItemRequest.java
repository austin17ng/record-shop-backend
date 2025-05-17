package me.austinng.recordshop.dto.order;

public record OrderItemRequest (
    long albumId,
    int quantity
) { }

