package com.example.spotifyplaylist.services.impl;

import com.example.spotifyplaylist.models.bindings.UserRegisterBindingModel;
import com.example.spotifyplaylist.models.entities.User;
import com.example.spotifyplaylist.repositories.UserRepository;
import com.example.spotifyplaylist.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean getByUsernameOrPassword(String username, String email) {

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
