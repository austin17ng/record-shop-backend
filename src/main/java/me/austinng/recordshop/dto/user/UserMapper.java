package me.austinng.recordshop.dto.user;

import me.austinng.recordshop.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
}
