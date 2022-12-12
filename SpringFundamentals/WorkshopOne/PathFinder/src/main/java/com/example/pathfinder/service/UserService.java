package com.example.pathfinder.service;

import com.example.pathfinder.model.bindings.UserRegisterBindingModel;
import com.example.pathfinder.model.services.UserServiceModel;

public interface UserService {
    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    UserServiceModel findUserByUserNameAndPassword(String userName, String password);
}
