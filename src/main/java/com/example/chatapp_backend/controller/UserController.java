package com.example.chatapp_backend.controller;

import com.example.chatapp_backend.dto.JwtResponseDTO;
import com.example.chatapp_backend.dto.UsersResponseDto;
import com.example.chatapp_backend.dto.UserRequestDto;
import com.example.chatapp_backend.service.JwtService;
import com.example.chatapp_backend.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users")
public class UserController {

    private UsersService usersService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    public UserController(UsersService usersService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.usersService = usersService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    @PostMapping("/register")
    public UsersResponseDto registerUser(@RequestBody UserRequestDto request) {
        return usersService.addUsers(request);
    }

    @PostMapping("/login")
    public JwtResponseDTO authenticateAndGetToken(@RequestBody UserRequestDto userRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestDto.getUserName(),userRequestDto.getPassword()));
        if(authentication.isAuthenticated()) {
            return new JwtResponseDTO(jwtService.generateToken(userRequestDto.getUserName()));
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("Ping", HttpStatus.OK);
    }
}
