package me.austinng.recordshop.dto;

import me.austinng.recordshop.model.Track;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackMapper {
    TrackDto toDto(Track track);
    Track toEntity(TrackDto trackDto);
}
