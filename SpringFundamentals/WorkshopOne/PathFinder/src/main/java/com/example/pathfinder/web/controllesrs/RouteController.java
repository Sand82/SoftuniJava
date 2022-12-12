package com.example.pathfinder.web.controllesrs;

import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {

        List<RouteViewModel> routeViewModel = routeService.findAllRoutesView();

        model.addAttribute("routes", routeViewModel);

        return "routes";
    }

    @GetMapping("/add")
    public String addRoutes() {


        return "add-routes";
    }
}
