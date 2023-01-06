package com.example.pathfinder.web.controllesrs;

import com.example.pathfinder.model.bindings.UserLoginBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;

@Controller
public class UserLoginController {

    @GetMapping("/users/login")
    public String login(){

       return "login";
    }
}
