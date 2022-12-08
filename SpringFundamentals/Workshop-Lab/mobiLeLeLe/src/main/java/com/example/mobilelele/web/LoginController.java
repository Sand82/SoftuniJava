package com.example.mobilelele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/users/login")
    public String login(){

        return "auth-login";
    }

    @GetMapping("users/register")
    public String register(){

        return "auth-register";
    }
}
