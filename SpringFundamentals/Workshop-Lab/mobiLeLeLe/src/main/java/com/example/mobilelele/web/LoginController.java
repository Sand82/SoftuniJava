package com.example.mobilelele.web;

import com.example.mobilelele.services.UserService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String showLogin() {

        return "auth-login";
    }

    @PostMapping("/users/login-error")
    public String failedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
            String userName,
            RedirectAttributes redirectAttributes
    ) {

        redirectAttributes.addFlashAttribute("bad_credentials", true )
                .addFlashAttribute("username", userName);

        return "redirect:/users/login";
    }
}
