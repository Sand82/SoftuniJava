package com.example.gamestore.services.impl;

import com.example.gamestore.entities.User;
import com.example.gamestore.entities.users.LoginDTO;
import com.example.gamestore.entities.users.RegisterDTO;
import com.example.gamestore.repositories.UserRepository;
import com.example.gamestore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDTO model) {

        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(model, User.class);

        long count = userRepository.count();

        if (count == 0) {

            user.setAdmin(true);
        }

        return userRepository.save(user);
    }

    @Override
    public User login(LoginDTO model) {

        return userRepository.findByEmailAndPassword(model.getEmail(), model.getPassword());
    }

    @Override
    public void logout() {

    }
}
