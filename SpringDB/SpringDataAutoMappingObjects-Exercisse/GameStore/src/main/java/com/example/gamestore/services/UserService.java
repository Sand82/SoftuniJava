package com.example.gamestore.services;

import com.example.gamestore.entities.User;
import com.example.gamestore.entities.users.LoginDTO;
import com.example.gamestore.entities.users.RegisterDTO;

public interface UserService {

    User register(RegisterDTO registerDTO);

    User login(LoginDTO loginDTO);

    void logout();
}
