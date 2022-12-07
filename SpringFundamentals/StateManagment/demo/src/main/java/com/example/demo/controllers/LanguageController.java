package com.example.demo.controllers;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LanguageController {

    private final String defaultLang = "bg";
    private final List<String> allLangs = List.of("bg", "en", "de");

    @GetMapping("/all")
    public String allLangs(Model model){

        model.addAttribute("all", allLangs);

        return "languages";
    }
}
