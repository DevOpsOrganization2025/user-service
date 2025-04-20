package com.dat.backend.userservice.service;

import com.dat.backend.userservice.dto.CreateUserDto;
import com.dat.backend.userservice.dto.UserDto;
import com.dat.backend.userservice.entity.User;
import com.dat.backend.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto create(CreateUserDto createUserDto) {
        User user = User.builder()
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .fullName(createUserDto.getFullName())
                .age(createUserDto.getAge())
                .build();
        userRepository.save(user);
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .age(user.getAge())
                .build();
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .age(user.getAge())
                        .build())
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .age(user.getAge())
                .build();
    }
}
