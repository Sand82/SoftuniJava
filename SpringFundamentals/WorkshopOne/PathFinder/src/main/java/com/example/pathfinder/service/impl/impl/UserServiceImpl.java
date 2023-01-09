package com.example.pathfinder.service.impl.impl;

import com.example.pathfinder.model.bindings.UserRegisterBindingModel;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.model.entities.enums.LevelEnum;
import com.example.pathfinder.model.services.UserServiceModel;
import com.example.pathfinder.model.view.UserViewModel;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {

        UserServiceModel userServiceModel = mapper.map(userRegisterBindingModel, UserServiceModel.class);

        User user = mapper.map(userServiceModel, User.class);

        user.setLevel(LevelEnum.BEGINNER);
        user.setPassword(userRegisterBindingModel.getPassword());

        userRepository.save(user);
    }

    @Override
    public UserViewModel createUserViewModel(Long id) {

        Optional<User> user = userRepository.findById(id);

        UserViewModel userViewModel = mapper.map(user, UserViewModel.class);

        return userViewModel;
    }

    @Override
    public User getById(Long id) {

        return userRepository.findById(id).get();
    }
}
