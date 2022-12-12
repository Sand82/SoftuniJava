package com.example.pathfinder.web.controllesrs;


import com.example.pathfinder.model.bindings.UserLoginBindingModel;
import com.example.pathfinder.model.bindings.UserRegisterBindingModel;
import com.example.pathfinder.model.services.UserServiceModel;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.CurrentUser;
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

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {

        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {

        return new UserLoginBindingModel();
    }


    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("isNotExist", false);

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        UserServiceModel user = userService.findUserByUserNameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (user == null) {

            redirectAttributes
                    .addFlashAttribute("isNotExist", true)
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        userService.loginUser(user.getId(), user.getUsername());

        return "redirect:/";
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

        userService.logout();

        return "redirect:/";
    }
}
