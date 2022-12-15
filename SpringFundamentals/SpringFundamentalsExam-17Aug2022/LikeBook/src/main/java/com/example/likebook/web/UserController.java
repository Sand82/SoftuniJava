package com.example.likebook.web;


import com.example.likebook.models.bindings.UserRegisterBindingModel;
import com.example.likebook.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String confirmPost(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors()) {

            setReturningModel(userRegisterBindingModel, bindingResult, redirectAttributes, false, false);

            return "redirect:register";
        }
        
        boolean isExistOnDatabase = userService.usernameAndEmailValidation(userRegisterBindingModel.getUsername(), userRegisterBindingModel.getEmail());

        if (isExistOnDatabase) {

            setReturningModel(userRegisterBindingModel, bindingResult, redirectAttributes, true, false);

            return "redirect:register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            setReturningModel(userRegisterBindingModel, bindingResult, redirectAttributes, false, true);

            return "redirect:register";
        }

        userService.addUserInDatabase(userRegisterBindingModel);

        return "redirect:login";
    }

    private static void setReturningModel(
            UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            boolean isExistOnDatabase, boolean isIncorrectPassword) {

        redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                          .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult)
                          .addFlashAttribute("isExistOnDatabase", isExistOnDatabase)
                          .addFlashAttribute("isIncorrectPassword", isIncorrectPassword);
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
