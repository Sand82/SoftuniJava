package com.example.mobilelele.web;

import com.example.mobilelele.model.binding.UserRegistrationBindingModel;
import com.example.mobilelele.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerUser(Model model) {

        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationBindingModel userModel) {

        userService.registerAndLoginUser(userModel);

        return "redirect:/";
    }
}
