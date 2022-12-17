package com.example.likebook.services.impl;

import com.example.likebook.models.bindings.UserRegisterBindingModel;
import com.example.likebook.models.entities.User;
import com.example.likebook.repositories.UserRepository;
import com.example.likebook.security.CurrentUser;
import com.example.likebook.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    private CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean usernameAndEmailValidation(String username, String email) {

        User user = userRepository.findByUsernameAndEmail(username, email).orElse(null);

        if (user == null) {

            return false;
        }

        return true;
    }

    @Override
    public void addUserInDatabase(UserRegisterBindingModel userRegisterBindingModel) {

        User user = mapper.map(userRegisterBindingModel, User.class);

        userRepository.save(user);
    }

    @Override
    public boolean loginUser(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username, password).orElse(null);

        if (user == null) {

            return false;
        }

        createSession(user);

        return true;
    }

    @Override
    public void logout() {

        currentUser.setId(null);
        currentUser.setUsername(null);
    }

    @Override
    public User getUserBuId(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    private void createSession(User user) {

        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
    }
}
