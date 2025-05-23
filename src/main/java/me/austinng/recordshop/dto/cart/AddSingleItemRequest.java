package me.austinng.recordshop.dto.cart;

import jakarta.validation.constraints.NotNull;

public record AddSingleItemRequest(
        @NotNull Long albumId
) {
}
