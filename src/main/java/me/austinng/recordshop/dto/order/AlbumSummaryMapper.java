package me.austinng.recordshop.dto.order;

import me.austinng.recordshop.model.Album;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlbumSummaryMapper {
    AlbumSummaryDto toAlbumSummaryDto(Album album);
}
