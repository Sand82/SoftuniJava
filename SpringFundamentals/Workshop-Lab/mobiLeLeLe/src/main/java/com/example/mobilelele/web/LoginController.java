package com.example.mobilelele.web;

import com.example.mobilelele.model.services.UserLoginServiceModel;
import com.example.mobilelele.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String showLogin() {

        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginServiceModel model){

        if (!userService.isAuthenticate(model.getUsername(), model.getPassword())) {

            userService.loginUser(model.getUsername());

            return "redirect:/users/login";
        }

        return "redirect:/";
    }
}
