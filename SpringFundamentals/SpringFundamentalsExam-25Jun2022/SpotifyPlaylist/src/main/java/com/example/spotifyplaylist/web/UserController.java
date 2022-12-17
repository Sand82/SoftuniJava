package com.example.spotifyplaylist.web;

import com.example.spotifyplaylist.models.bindings.UserLoginBindingModel;
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
    public String login(Model model) {

        if (!model.containsAttribute("isFound")) {

            model.addAttribute("isFound", true);
        }

        return "login";
    }

    @PostMapping("/login")
    public String confirmLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        if (bindingResult.hasFieldErrors()) {

            setReturningLoginModel(userLoginBindingModel, bindingResult, redirectAttributes, true);

            return "redirect:login";
        }

        boolean isFound = userService.getByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (!isFound) {

            setReturningLoginModel(userLoginBindingModel, bindingResult, redirectAttributes, false);

            return "redirect:login";
        }

        return "redirect:/";
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

            setReturningRegisterModel(userRegisterBindingModel, bindingResult, redirectAttributes, true, false);

            return "redirect:register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            setReturningRegisterModel(userRegisterBindingModel, bindingResult, redirectAttributes, false, false);

            return "redirect:register";
        }

        boolean isExist = userService.getByUsernameOrEmail(userRegisterBindingModel.getUsername(), userRegisterBindingModel.getEmail());


        if (isExist) {

            setReturningRegisterModel(userRegisterBindingModel, bindingResult, redirectAttributes, true, true);

            return "redirect:register";
        }

        userService.createUser(userRegisterBindingModel);

        return "redirect:login";
    }

    private static void setReturningLoginModel(UserLoginBindingModel userLoginBindingModel,
                                               BindingResult bindingResult,
                                               RedirectAttributes redirectAttributes,
                                               boolean isFound) {
        redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult)
                .addFlashAttribute("isFound", isFound);
    }


    private static void setReturningRegisterModel(UserRegisterBindingModel userRegisterBindingModel,
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

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {

        return new UserLoginBindingModel();
    }
}
