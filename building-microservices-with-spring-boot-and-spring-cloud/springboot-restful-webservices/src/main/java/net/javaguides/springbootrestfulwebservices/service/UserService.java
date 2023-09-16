package net.javaguides.springbootrestfulwebservices.service;

import net.javaguides.springbootrestfulwebservices.dto.UserDto;

import java.util.List;

/**
 * @author vsushko
 */
public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long userId);
}
