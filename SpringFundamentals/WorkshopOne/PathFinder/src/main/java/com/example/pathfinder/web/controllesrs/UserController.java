package com.example.pathfinder.web.controllesrs;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/login")
    public String login() {


        return login();
    }

    @PostMapping("/login")
    public String loginConfirm() {

        return "redirect:/";
    }


    @GetMapping("/register")
    public String register(){


        return register();
    }

    @PostMapping
    public String registerConfirm() {

        return "redirect:/";
    }
}
