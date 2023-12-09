package com.example.chatapp_backend.service;

import com.example.chatapp_backend.configuration.UserInfoUserDetails;
import com.example.chatapp_backend.entity.User;
import com.example.chatapp_backend.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByUserName(username)
                                   .orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));

        return UserInfoUserDetails
                .builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }
}
