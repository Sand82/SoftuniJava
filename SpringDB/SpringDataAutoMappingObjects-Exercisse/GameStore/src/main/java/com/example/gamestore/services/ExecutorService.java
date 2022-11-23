package com.example.gamestore.services;

public interface ExecutorService {

    String REGISTER_USER_COMMAND = "RegisterUser";
    String LOGIN_USER_COMMAND = "LoginUser";
    final String LOGOUT_USER_COMMAND = "Logout";

    String execute(String command);
}
