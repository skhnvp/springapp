package ru.testproj.springapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testproj.springapp.dto.user.UserCreateDto;
import ru.testproj.springapp.dto.user.UserDto;
import ru.testproj.springapp.mapper.UserMapper;
import ru.testproj.springapp.dto.user.UserUpdateDto;
import ru.testproj.springapp.entity.User;
import ru.testproj.springapp.repository.UserRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDto create(UserCreateDto dto) {
        if (userRepository.findByEmail(dto.getEmail()) != null) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        User user = userMapper.toEntity(dto);
        user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());

        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserDto update(Long id, UserUpdateDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(dto.getEmail()) != null) {
                throw new IllegalArgumentException("Email already in use");
            }
        }

        userMapper.updateEntityFromDto(dto, user);
        return userMapper.toDto(user);
    }

    public void delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("User with this id does not exist");
        }
        userRepository.deleteById(id);
    }
}
