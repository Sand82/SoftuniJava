package com.example.coffeeshop.web;

import com.example.coffeeshop.models.bindings.UserLoginBindingModel;
import com.example.coffeeshop.models.bindings.UserRegisterBindingModel;
import com.example.coffeeshop.models.entities.User;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.services.UserService;
import jakarta.servlet.http.HttpSession;
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

    private static final String isNotMatch = "isNotMatch";
    private UserService userService;
    private CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        userService.createUser(userRegisterBindingModel);

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {

        if (!model.containsAttribute(isNotMatch)) {

            model.addAttribute(isNotMatch, false);
        }

        return "login";
    }

    @PostMapping("/login")
    public String confirmLogin(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors()) {

            setReturningModel(userLoginBindingModel, bindingResult, redirectAttributes, false);

            return "redirect:login";
        }

        boolean isMatch = userService.userExist(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (!isMatch) {

            setReturningModel(userLoginBindingModel, bindingResult, redirectAttributes, true);

            return "redirect:login";
        }

        return "redirect:/";
    }

    private void setCurrentUser(User user) {

        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());

        System.out.println();
    }

    private static void setReturningModel(UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, boolean isNotMatch) {
        redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult)
                .addFlashAttribute("isNotMatch", isNotMatch);
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {

        if (currentUser != null) {

            userService.logout(httpSession);
        }

        return "redirect:/";
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
