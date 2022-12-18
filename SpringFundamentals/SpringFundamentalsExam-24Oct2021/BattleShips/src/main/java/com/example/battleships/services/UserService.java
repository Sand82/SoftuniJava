package com.example.battleships.services;

import com.example.battleships.models.bindings.UserRegisterBindingModel;

public interface UserService {
    boolean getByUsernameAndEmail(String username, String password);

    void createUser(UserRegisterBindingModel userRegisterBindingModel);
}
