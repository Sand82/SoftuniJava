package com.example.likebook.models.bindings;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterBindingModel {

    @Size(min = 3, max = 20)
    @NotNull
    private String username;
    @Size(min = 3, max = 20)
    @NotNull
    private String password;
    @Size(min = 3, max = 20)
    @NotNull
    private String confirmPassword;
    @Email
    @NotNull
    private String email;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
