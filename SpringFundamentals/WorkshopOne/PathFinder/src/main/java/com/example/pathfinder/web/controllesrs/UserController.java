package com.example.pathfinder.web.controllesrs;


import com.example.pathfinder.model.bindings.UserRegisterBindingModel;
import com.example.pathfinder.service.UserService;
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
    public UserRegisterBindingModel userRegisterBindingModel () {

        return  new UserRegisterBindingModel();
    }

    @GetMapping("/login")
    public String login() {


        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm() {

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){

//        if (!model.containsAttribute("userRegisterBindingModel")) {
//
//            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
//        }

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

        return "redirect:/";
    }
}
