package com.example.spotifyplaylist.web;

import com.example.spotifyplaylist.models.views.AllSongViewModel;
import com.example.spotifyplaylist.services.SongService;
import com.example.spotifyplaylist.services.UserService;
import com.example.spotifyplaylist.seurity.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final SongService songService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, SongService songService, UserService userService) {
        this.currentUser = currentUser;
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {

        if (currentUser.getId() == null) {

            return "index";
        }

        List<AllSongViewModel> allSongs = songService.getAllSongs();

        List<AllSongViewModel> popSongs = allSongs.stream().filter(s -> s.getStyle().getName().name().equals("POP")).toList();
        List<AllSongViewModel> rockSongs = allSongs.stream().filter(s -> s.getStyle().getName().name().equals("ROCK")).toList();
        List<AllSongViewModel> jazzSongs = allSongs.stream().filter(s -> s.getStyle().getName().name().equals("JAZZ")).toList();

        List<AllSongViewModel> userSongs = userService.getUserList();

        int totalDurationTime = userSongs.stream().mapToInt(AllSongViewModel::getDuration).sum();

        model.addAttribute("popSongs", popSongs);
        model.addAttribute("rockSongs", rockSongs);
        model.addAttribute("jazzSongs", jazzSongs);
        model.addAttribute("userSongs", userSongs);
        model.addAttribute("totalDurationTime", totalDurationTime);

        return "home";
    }
}
