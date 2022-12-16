package com.example.likebook.web;

import com.example.likebook.models.bindings.MoodAddBindingModel;
import com.example.likebook.services.MoodService;
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
@RequestMapping("/posts")
public class PostController {

    private MoodService moodService;

    public PostController(MoodService moodService) {

        this.moodService = moodService;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("isExist")) {

            model.addAttribute("isExist", true);
        }

        return "post-add";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid MoodAddBindingModel moodAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors()) {

            setReturningModel(moodAddBindingModel, bindingResult, redirectAttributes, true);

            return "redirect:post-add";
        }

        moodService.createMood(moodAddBindingModel);

        return "redirect:/";
    }

    private static void setReturningModel(MoodAddBindingModel moodAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, boolean isExist) {

        redirectAttributes.addFlashAttribute("moodAddBindingModel", moodAddBindingModel)
                .addFlashAttribute("org.springframework.validation.BindingResult.moodAddBindingModel", bindingResult)
                .addFlashAttribute("isExist", isExist);
    }

    @ModelAttribute
    public MoodAddBindingModel moodAddBindingModel() {
        return new MoodAddBindingModel();
    }
}
