package com.example.battleships.services.impl;

import com.example.battleships.models.bindings.UserRegisterBindingModel;
import com.example.battleships.models.entities.User;
import com.example.battleships.repositories.UserRepository;
import com.example.battleships.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean getByUsernameAndEmail(String username, String email) {

        User user = userRepository.findByUsernameOrEmail(username, email);

        if (user == null) {

            return false;
        }

        return true;
    }

    @Override
    public void createUser(UserRegisterBindingModel userRegisterBindingModel) {

        User user = mapper.map(userRegisterBindingModel, User.class);

        userRepository.save(user);
    }
}
