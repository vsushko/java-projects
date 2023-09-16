package net.javaguides.springbootrestfulwebservices.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springbootrestfulwebservices.dto.UserDto;
import net.javaguides.springbootrestfulwebservices.entity.User;
import net.javaguides.springbootrestfulwebservices.mapper.UserMapper;
import net.javaguides.springbootrestfulwebservices.repository.UserRepository;
import net.javaguides.springbootrestfulwebservices.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author vsushko
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = UserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return UserMapper.MAPPER.mapToUserDto(userOptional.get());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto UserDto) {
        User dbUser = userRepository.findById(UserDto.getId()).get();
        dbUser.setFirstName(UserDto.getFirstName());
        dbUser.setLastName(UserDto.getLastName());
        dbUser.setEmail(UserDto.getEmail());
        return UserMapper.MAPPER.mapToUserDto(userRepository.save(dbUser));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
