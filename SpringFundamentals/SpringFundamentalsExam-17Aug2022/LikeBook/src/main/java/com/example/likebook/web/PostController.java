package com.example.likebook.web;

import com.example.likebook.models.bindings.MoodAddBindingModel;
import com.example.likebook.services.MoodService;
import com.example.likebook.services.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class PostController {

    private MoodService moodService;
    private final PostService postService;

    public PostController(MoodService moodService, PostService postService) {

        this.moodService = moodService;
        this.postService = postService;
    }

    @GetMapping("/likes")
    public String likes(){

        return  "redirect:/";
    }

    @GetMapping("/add")
    public String add(Model model) {

        return "post-add";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid MoodAddBindingModel moodAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors()) {

            redirectAttributes.addFlashAttribute("moodAddBindingModel", moodAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.moodAddBindingModel", bindingResult);

            return "redirect:add";
        }

        moodService.createMood(moodAddBindingModel);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        postService.deletePost(id);

        return  "redirect:/";
    }

    @ModelAttribute
    public MoodAddBindingModel moodAddBindingModel() {
        return new MoodAddBindingModel();
    }
}
