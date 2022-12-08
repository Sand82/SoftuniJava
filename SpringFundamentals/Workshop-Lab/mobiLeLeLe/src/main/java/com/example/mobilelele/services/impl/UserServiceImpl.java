package com.example.mobilelele.services.impl;

import com.example.mobilelele.model.entities.UserEntity;
import com.example.mobilelele.repositories.UserRepository;
import com.example.mobilelele.security.CurrentUser;
import com.example.mobilelele.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public boolean isAuthenticate(String username, String password) {

        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {

            return false;
        }

        return passwordEncoder.matches(password, user.get().getPassword());
    }

    @Override
    public void loginUser(String userName) {

        currentUser.setAnonymous(false).setName(userName);
    }
}
