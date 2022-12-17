package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.models.bindings.UserRegisterBindingModel;

public interface UserService {
    boolean getByUsernameOrPassword(String username, String email);

    void createUser(UserRegisterBindingModel userRegisterBindingModel);
}
