package com.example.mobilelele.services;

public interface UserService {

    boolean isAuthenticate(String userName, String password);

    void loginUser(String userName);
}
