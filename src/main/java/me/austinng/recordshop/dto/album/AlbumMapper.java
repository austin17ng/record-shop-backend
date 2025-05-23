package me.austinng.recordshop.dto.album;

import me.austinng.recordshop.dto.track.TrackMapper;
import me.austinng.recordshop.model.Album;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TrackMapper.class, ArtistMapper.class})
public interface AlbumMapper {
    AlbumDto toDto(Album album);
    Album toEntity(AlbumDto albumDto);
}
