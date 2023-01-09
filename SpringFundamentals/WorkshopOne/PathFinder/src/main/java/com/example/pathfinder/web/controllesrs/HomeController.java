package com.example.pathfinder.web.controllesrs;

import com.example.pathfinder.service.impl.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    public PictureService pictureService;

    public HomeController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<String> picturesModel = pictureService.findAllUrls();

        model.addAttribute("pictures", picturesModel);

        return "index";
    }

    @GetMapping("/about")
    public String about() {

        return "about";
    }
}
