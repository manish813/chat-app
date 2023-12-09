package com.example.chatapp_backend.service;

import com.example.chatapp_backend.dto.UsersResponseDto;
import com.example.chatapp_backend.entity.User;
import com.example.chatapp_backend.dto.UserRequestDto;
import com.example.chatapp_backend.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsersResponseDto addUsers(UserRequestDto userRequest) {
        User newUser = new User();
        newUser.setUserName(userRequest.getUserName());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User result = usersRepository.save(newUser);
        return new UsersResponseDto(result.getUserName(), result.getUserId());
    }
}
