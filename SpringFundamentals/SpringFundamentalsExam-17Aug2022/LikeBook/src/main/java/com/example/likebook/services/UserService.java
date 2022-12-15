package com.example.likebook.services;

import com.example.likebook.models.bindings.UserRegisterBindingModel;

public interface UserService {
    boolean usernameAndEmailValidation(String username, String email);

    void addUserInDatabase(UserRegisterBindingModel userRegisterBindingModel);
}
