package com.example.mobilelele.web;

import com.example.mobilelele.services.StatsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatsController {

    private StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/statistics")
    public ModelAndView stats(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stats", statsService.getStats());
        modelAndView.setViewName("stats");

        return modelAndView;
    }
}
