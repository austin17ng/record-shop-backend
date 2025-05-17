package me.austinng.recordshop.dto.order;

public record OrderItemDto(
        long id,
        AlbumSummaryDto album
) {}
