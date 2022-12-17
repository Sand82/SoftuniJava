package com.example.likebook.web;

import com.example.likebook.models.views.MyPostViewModel;
import com.example.likebook.security.CurrentUser;
import com.example.likebook.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private CurrentUser currentUser;
    private PostService postService;

    public HomeController(CurrentUser currentUser, PostService postService) {
        this.currentUser = currentUser;
        this.postService = postService;
    }

    @GetMapping("/")
    public String index(Model model) {


        if (currentUser.getId() == null) {
            return "index";
        }

        List<MyPostViewModel> myPosts = postService.getMyPosts(currentUser.getId());

        String username = currentUser.getUsername();

        model.addAttribute("myPosts", myPosts);
        model.addAttribute("username", username);

        return "home";
    }
}
