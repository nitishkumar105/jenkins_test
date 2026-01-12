package com.jenkins.dev.service;

import com.jenkins.dev.dto.UserRequestDto;
import com.jenkins.dev.dto.UserResponseDto;
import com.jenkins.dev.model.User;
import com.jenkins.dev.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(UserRequestDto request) {


        // check duplicate name
        if (userRepository.existsByName(request.getName())) {
            throw new RuntimeException("User already exists with name: " + request.getName());
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // later hash it

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    //  Get All Users
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }
}
