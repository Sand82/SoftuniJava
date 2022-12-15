package com.example.likebook.web;


import com.example.likebook.models.bindings.UserLoginBindingModel;
import com.example.likebook.models.bindings.UserRegisterBindingModel;
import com.example.likebook.services.UserService;
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
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {

        if (!model.containsAttribute("isExist")) {

            model.addAttribute("isExist", true);
        }

        return "login";
    }


    @PostMapping("/register")
    public String confirmPost(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors()) {

            setReturningRegisterModel(userRegisterBindingModel, bindingResult, redirectAttributes, false, false);

            return "redirect:register";
        }
        
        boolean isExistOnDatabase = userService.usernameAndEmailValidation(userRegisterBindingModel.getUsername(), userRegisterBindingModel.getEmail());

        if (isExistOnDatabase) {

            setReturningRegisterModel(userRegisterBindingModel, bindingResult, redirectAttributes, true, false);

            return "redirect:register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            setReturningRegisterModel(userRegisterBindingModel, bindingResult, redirectAttributes, false, true);

            return "redirect:register";
        }

        userService.addUserInDatabase(userRegisterBindingModel);

        return "redirect:login";
    }

    @PostMapping("/login")
    public String confirmLogin(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors()) {

            serReturningLoginModel(userLoginBindingModel, bindingResult, redirectAttributes, true);

            return "redirect:login";
        }

        boolean isExist = userService.loginUser(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (!isExist) {

            serReturningLoginModel(userLoginBindingModel, bindingResult, redirectAttributes, false);

            return "redirect:login";
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

    private static void setReturningRegisterModel(
            UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            boolean isExistOnDatabase, boolean isIncorrectPassword) {

        redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult)
                .addFlashAttribute("isExistOnDatabase", isExistOnDatabase)
                .addFlashAttribute("isIncorrectPassword", isIncorrectPassword);
    }

    private static void serReturningLoginModel(
            UserLoginBindingModel userLoginBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            boolean isExist) {

        redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult)
                .addFlashAttribute("isExist", isExist);
    }
}
