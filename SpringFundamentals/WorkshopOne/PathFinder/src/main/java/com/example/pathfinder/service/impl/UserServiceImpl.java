package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.bindings.UserRegisterBindingModel;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.model.entities.enums.LevelEnum;
import com.example.pathfinder.model.services.UserServiceModel;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {

        UserServiceModel userServiceModel = mapper.map(userRegisterBindingModel, UserServiceModel.class);

        User user = mapper.map(userServiceModel, User.class);

        user.setLevel(LevelEnum.BEGINNER);

        userRepository.save(user);
    }
}
