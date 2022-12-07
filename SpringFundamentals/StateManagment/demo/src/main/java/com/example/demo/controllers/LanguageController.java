package com.example.demo.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class LanguageController {

    private final String defaultLang = "bg";
    private final List<String> allLangs = List.of("en", "bg", "de");

    @PostMapping("/save")
    public String save(@RequestParam String lang, HttpServletResponse response, HttpSession session) {

//        Cookie cookie = new Cookie("langCookie", lang);
//        response.addCookie(cookie);

        session.setAttribute("lang", lang);

        return "redirect:/all";
    }

    @GetMapping("/all")
   //public String allLangs(Model model, @CookieValue(value = "langCookie", required = false, defaultValue = defaultLang) String lang){
        public String allLangs(Model model, HttpSession session){

        Object preferredLang = session.getAttribute("lang");

        if (preferredLang == null) {

            preferredLang= defaultLang;
        }


        model.addAttribute("all", allLangs);

        model.addAttribute("preferredLang", preferredLang);

        return "languages";
    }
}
