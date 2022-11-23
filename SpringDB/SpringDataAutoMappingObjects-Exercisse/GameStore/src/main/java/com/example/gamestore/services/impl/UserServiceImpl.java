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

    private User user = null;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDTO model) {

        ModelMapper mapper = new ModelMapper();
        user = mapper.map(model, User.class);

        long count = userRepository.count();

        if (count == 0) {

            user.setAdmin(true);
        }

        return userRepository.save(user);
    }

    @Override
    public User login(LoginDTO model) {

        user =  userRepository.findByEmailAndPassword(model.getEmail(), model.getPassword());
        return user;
    }

    @Override
    public String logout() {

        if (user == null) {

            return "No logged user";
        }

        String userName = user.getFullName();

        this.user = null;

        return "Successfully logout the " + userName;
    }
}
