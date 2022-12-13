package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.bindings.UserRegisterBindingModel;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.model.entities.enums.LevelEnum;
import com.example.pathfinder.model.services.UserServiceModel;
import com.example.pathfinder.model.view.UserViewModel;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
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
    public UserServiceModel findUserByUserNameAndPassword(String userName, String password) {

        return userRepository.findByUsernameAndPassword(userName, password).stream().map(u -> mapper.map(u, UserServiceModel.class)).findFirst().orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {

        currentUser
                .setId(id)
                .setUsername(username);
    }

    @Override
    public void logout() {

        currentUser.setUsername(null);
        currentUser.setId(null);
    }

    @Override
    public UserViewModel createUserViewModel(Long id) {

        Optional<User> user = userRepository.findById(id);

        UserViewModel userViewModel = mapper.map(user, UserViewModel.class);

        return userViewModel;
    }

    @Override
    public boolean isNameExists(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public User getById(Long id) {

        return userRepository.findById(id).get();
    }
}
