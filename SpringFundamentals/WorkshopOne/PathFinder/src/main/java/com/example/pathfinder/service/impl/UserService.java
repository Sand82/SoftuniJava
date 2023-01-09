package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.bindings.UserRegisterBindingModel;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.model.services.UserServiceModel;
import com.example.pathfinder.model.view.UserViewModel;

public interface UserService {
    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    UserViewModel createUserViewModel(Long id);

    User getById(Long id);
}
