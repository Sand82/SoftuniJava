package com.example.pathfinder.service;

import com.example.pathfinder.model.bindings.UserRegisterBindingModel;
import com.example.pathfinder.model.services.UserServiceModel;
import com.example.pathfinder.model.view.UserViewModel;

public interface UserService {
    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    UserServiceModel findUserByUserNameAndPassword(String userName, String password);

    void loginUser(Long id, String username);

    void logout();

    UserViewModel createUserViewModel(Long id);

    boolean isNameExists(String username);
}
