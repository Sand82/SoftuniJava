package com.example.pathfinder.model.bindings;

import jakarta.validation.constraints.Size;

public class UserLoginBindingModel {

    @Size(min = 4)
    private String username;

    @Size(min = 4)
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
