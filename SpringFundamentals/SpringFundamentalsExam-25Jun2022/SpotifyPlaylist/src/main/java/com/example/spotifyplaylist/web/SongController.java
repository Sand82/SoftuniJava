package com.example.spotifyplaylist.web;

import com.example.spotifyplaylist.models.bindings.SongAddBindingModel;
import com.example.spotifyplaylist.services.SongService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/add")
    public String add() {

        return "song-add";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid SongAddBindingModel songAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasFieldErrors()) {

            redirectAttributes.addFlashAttribute("songAddBindingModel", songAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.songAddBindingModel", bindingResult);

            return "redirect:add";
        }

        songService.createSong(songAddBindingModel);

        return "redirect:/";
    }

    @ModelAttribute
    public SongAddBindingModel songAddBindingModel() {

        return new SongAddBindingModel();
    }
}
