package com.example.spotifyplaylist.web;

import com.example.spotifyplaylist.models.bindings.UserRegisterBindingModel;
import com.example.spotifyplaylist.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {


        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (!model.containsAttribute("isValidPasswordConfirmation")) {

            model.addAttribute("isValidPasswordConfirmation", true);
        }

        if (!model.containsAttribute("isExist")) {

            model.addAttribute("isExist", false);
        }

        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(
            @Valid UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            setReturningModel(userRegisterBindingModel, bindingResult, redirectAttributes, true, false);

            return "redirect:register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            setReturningModel(userRegisterBindingModel, bindingResult, redirectAttributes, false, false);

            return "redirect:register";
        }

        boolean isExist = userService.getByUsernameOrPassword(userRegisterBindingModel.getUsername(), userRegisterBindingModel.getEmail());


        if (isExist) {

            setReturningModel(userRegisterBindingModel, bindingResult, redirectAttributes, true, true);

            return "redirect:register";
        }

        userService.createUser(userRegisterBindingModel);

        return "redirect:login";
    }

    private static void setReturningModel(UserRegisterBindingModel userRegisterBindingModel,
                                          BindingResult bindingResult,
                                          RedirectAttributes redirectAttributes,
                                          boolean isValidPasswordConfirmation,
                                          boolean isExist) {

        redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult)
                .addFlashAttribute("isValidPasswordConfirmation", isValidPasswordConfirmation)
                .addFlashAttribute("isExist", isExist);
    }

    @GetMapping("/logout")
    public String logout() {


        return "login";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {

        return new UserRegisterBindingModel();
    }
}
