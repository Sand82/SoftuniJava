package com.example.battleships.web;

import com.example.battleships.models.bindings.UserLoginBindingModel;
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

    @GetMapping("/login")
    public String login(Model model) {

        if (!model.containsAttribute("isExist")) {

            model.addAttribute("isExist", true);
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors()) {

            setReturningLoginModel(userLoginBindingModel, bindingResult, redirectAttributes, true);

            return "redirect:login";
        }

        boolean isExist = userService.getByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (!isExist) {

            setReturningLoginModel(userLoginBindingModel, bindingResult, redirectAttributes, false);

            return "redirect:login";
        }

        return "redirect:/";
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

    @GetMapping("/logout")
    public String logout(){

        userService.logout();

        return "redirect:login";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {

        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {

        return new UserLoginBindingModel();
    }

    private static void setReturningLoginModel(UserLoginBindingModel userLoginBindingModel,
                                               BindingResult bindingResult,
                                               RedirectAttributes redirectAttributes,
                                               boolean isExist) {
        redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult)
                .addFlashAttribute("isExist", isExist);
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
}
