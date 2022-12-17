package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.models.bindings.UserRegisterBindingModel;

public interface UserService {
    boolean getByUsernameOrEmail(String username, String email);

    void createUser(UserRegisterBindingModel userRegisterBindingModel);

    boolean getByUsernameAndPassword(String username, String password);

    void logout();
}
