package com.example.pathfinder.web.controllesrs;

import com.example.pathfinder.model.bindings.UserLoginBindingModel;
import com.example.pathfinder.model.bindings.UserRegisterBindingModel;
import com.example.pathfinder.model.services.UserServiceModel;
import com.example.pathfinder.model.view.UserViewModel;
import com.example.pathfinder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {

        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register(Model model) {

        //Create and inject new instance of UserRegisterBindingModel model from userRegisterBindingModel method with annotation @ModelAttribute.

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        userService.registerUser(userRegisterBindingModel);

        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout() {

        //userService.logout();

        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model) {

        UserViewModel userViewModel = userService.createUserViewModel(id);

        model.addAttribute("userViewModel", userViewModel);

        return "profile";
    }
}
