package com.example.mobilelele.web;


import com.example.mobilelele.model.binding.UserRegistrationBindingModel;
import com.example.mobilelele.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/register")
    public String registerUser() {

        return "auth-register";
    }

    @GetMapping("/users/register")
    public String register(@Valid UserRegistrationBindingModel userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()|| !userModel.getPassword().equals(userModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/users/register";
        }

//        UserRegisterServiceModel serviceModel = mapper.map(userModel, UserRegisterServiceModel.class);
//
//        userService.registerAndLoginUser(serviceModel);

        return "redirect:/";
    }
}
