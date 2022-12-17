package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.models.bindings.UserRegisterBindingModel;
import com.example.spotifyplaylist.models.entities.Song;
import com.example.spotifyplaylist.models.entities.User;

public interface UserService {
    boolean getByUsernameOrEmail(String username, String email);

    void createUser(UserRegisterBindingModel userRegisterBindingModel);

    boolean getByUsernameAndPassword(String username, String password);

    void logout();

    void saveSong(Song song);
}
