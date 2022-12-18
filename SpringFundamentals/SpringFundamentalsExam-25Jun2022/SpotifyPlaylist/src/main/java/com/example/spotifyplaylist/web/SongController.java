package com.example.spotifyplaylist.web;

import com.example.spotifyplaylist.models.bindings.SongAddBindingModel;
import com.example.spotifyplaylist.services.SongService;
import com.example.spotifyplaylist.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private UserService userService;

    public SongController(SongService songService, UserService userService) {
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String add() {

        return "song-add";
    }

    @GetMapping("/addSong/{id}")
    public String addSong(@PathVariable Long id) {

        songService.addSong(id);

        return "redirect:/";
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

    @GetMapping("/removeAll")
    public String removeAll() {

        songService.removeAllSongs();

        return "redirect:/";
    }

    @ModelAttribute
    public SongAddBindingModel songAddBindingModel() {

        return new SongAddBindingModel();
    }
}
