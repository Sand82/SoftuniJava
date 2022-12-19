package com.example.battleships.web;

import com.example.battleships.models.views.AllShipsViewModel;
import com.example.battleships.models.views.ShipViewModel;
import com.example.battleships.security.CurrentUser;
import com.example.battleships.services.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private CurrentUser currentUser;
    private ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }

    @GetMapping("/")
    public String home(Model model) {

        if (currentUser.getId() == null) {

            return "index";
        }

        List<ShipViewModel> attackers = shipService.setAttackers();

        List<ShipViewModel> defenders = shipService.setDefenders();

        List<AllShipsViewModel> allShips = shipService.getAllModels();

        model.addAttribute("attackers", attackers);
        model.addAttribute("defenders", defenders);
        model.addAttribute("allShips", allShips);

        return "home";
    }
}
