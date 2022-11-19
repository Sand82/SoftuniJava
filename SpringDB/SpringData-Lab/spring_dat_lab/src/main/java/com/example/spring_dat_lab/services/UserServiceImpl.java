package com.example.spring_dat_lab.services;

import com.example.spring_dat_lab.models.User;
import com.example.spring_dat_lab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) {

        User cureUser = userRepository.findByUsername(user.getUsername());

        if (cureUser == null) {

            this.userRepository.save(user);
        }
    }
}
