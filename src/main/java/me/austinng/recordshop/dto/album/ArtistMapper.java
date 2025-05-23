package me.austinng.recordshop.dto.album;

import me.austinng.recordshop.model.Artist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    ArtistDto toArtistDto(Artist artist);
    Artist toEntity(ArtistDto artistDto);
}
