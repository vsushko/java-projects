package net.javaguides.springbootrestfulwebservices.mapper;

import net.javaguides.springbootrestfulwebservices.dto.UserDto;
import net.javaguides.springbootrestfulwebservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Student mapper.
 *
 * @author vsushko
 */
@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
