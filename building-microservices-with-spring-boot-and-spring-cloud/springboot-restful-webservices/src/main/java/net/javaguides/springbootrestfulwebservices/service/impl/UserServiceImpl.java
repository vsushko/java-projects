package net.javaguides.springbootrestfulwebservices.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springbootrestfulwebservices.dto.UserDto;
import net.javaguides.springbootrestfulwebservices.entity.User;
import net.javaguides.springbootrestfulwebservices.exception.EmailAlreadyExistsException;
import net.javaguides.springbootrestfulwebservices.exception.ResourceNotFoundException;
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

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists for User");
        }

        User savedUser = userRepository.save(user);

        return UserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User dbUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        dbUser.setFirstName(userDto.getFirstName());
        dbUser.setLastName(userDto.getLastName());
        dbUser.setEmail(userDto.getEmail());
        return UserMapper.MAPPER.mapToUserDto(userRepository.save(dbUser));
    }

    @Override
    public void deleteUser(Long userId) {
        User dbUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(dbUser.getId());
    }
}
