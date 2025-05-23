package me.austinng.recordshop.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddItemRequest(
     @NotNull Long albumId,
     @Min(1) Integer quantity
) {}
