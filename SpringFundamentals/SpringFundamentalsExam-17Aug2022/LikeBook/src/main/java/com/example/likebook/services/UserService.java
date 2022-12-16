package com.example.likebook.services;

import com.example.likebook.models.bindings.UserRegisterBindingModel;
import com.example.likebook.models.entities.User;

public interface UserService {
    boolean usernameAndEmailValidation(String username, String email);

    void addUserInDatabase(UserRegisterBindingModel userRegisterBindingModel);

    boolean loginUser(String username, String password);

}
