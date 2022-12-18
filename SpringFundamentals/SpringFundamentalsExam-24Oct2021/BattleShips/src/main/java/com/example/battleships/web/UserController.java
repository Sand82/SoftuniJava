package com.example.battleships.web;

import com.example.battleships.models.bindings.UserRegisterBindingModel;
import com.example.battleships.services.UserService;
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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (!model.containsAttribute("isNotValidPassConfirmation")) {

            model.addAttribute("isNotValidPassConfirmation", false);
        }

        if (!model.containsAttribute("isExist")) {

            model.addAttribute("isExist", false);
        }

        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors()) {

            setReturningRegisterModel(userRegisterBindingModel, bindingResult, redirectAttributes, false, false);

            return "redirect:register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            setReturningRegisterModel(userRegisterBindingModel, bindingResult, redirectAttributes, true, false);

            return "redirect:register";
        }

        boolean isExist = userService.getByUsernameAndEmail(userRegisterBindingModel.getUsername(), userRegisterBindingModel.getEmail());

        if (isExist) {

            setReturningRegisterModel(userRegisterBindingModel, bindingResult, redirectAttributes, false, true);

            return "redirect:register";
        }

        userService.createUser(userRegisterBindingModel);

        return "login";
    }

    private static void setReturningRegisterModel(UserRegisterBindingModel userRegisterBindingModel,
                                                  BindingResult bindingResult,
                                                  RedirectAttributes redirectAttributes,
                                                  boolean isNotValidPassConfirmation,
                                                  boolean isExist) {
        redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
            .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult)
            .addFlashAttribute("isNotValidPassConfirmation", isNotValidPassConfirmation)
            .addFlashAttribute("isExist", isExist);
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {

        return new UserRegisterBindingModel();
    }
}
