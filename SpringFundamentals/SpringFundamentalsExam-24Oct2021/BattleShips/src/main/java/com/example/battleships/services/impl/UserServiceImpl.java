package com.example.battleships.services.impl;

import com.example.battleships.models.bindings.UserRegisterBindingModel;
import com.example.battleships.models.entities.User;
import com.example.battleships.repositories.UserRepository;
import com.example.battleships.security.CurrentUser;
import com.example.battleships.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
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

    @Override
    public boolean getByUsernameAndPassword(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) {

            return false;
        }

        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());

        return true;
    }

    @Override
    public void logout() {

        currentUser.setId(null);
        currentUser.setUsername(null);
    }

    @Override
    public User getById() {

        return userRepository.findById(currentUser.getId()).orElse(null);
    }
}
