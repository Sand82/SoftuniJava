package com.example.coffeeshop.models.bindings;

import jakarta.validation.constraints.Size;

public class UserLoginBindingModel {

    @Size(min = 5, max = 20, message = "Invalid username.")
    private String username;

    @Size(min = 3, message = "Invalid password.")
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
