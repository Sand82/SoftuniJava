package com.example.coffeeshop.services;

import com.example.coffeeshop.models.bindings.UserRegisterBindingModel;
import com.example.coffeeshop.models.entities.User;
import com.example.coffeeshop.models.views.UserOrdersViewModel;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {
    void createUser(UserRegisterBindingModel userRegisterBindingModel);
    boolean userExist(String username, String password);
    void logout(HttpSession httpSession);
    User getById(Long id);
    List<UserOrdersViewModel> getAllModels();
}
