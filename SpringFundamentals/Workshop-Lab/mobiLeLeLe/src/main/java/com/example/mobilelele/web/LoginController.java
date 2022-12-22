package com.example.mobilelele.web;

import com.example.mobilelele.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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
}
