package me.austinng.recordshop.dto.cart;

public record AddToCartRequest (
     long albumId,
     int quantity
) {}
