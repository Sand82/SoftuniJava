package com.example.pathfinder.web.controllesrs;

import com.example.pathfinder.model.bindings.RouteAddBindingModel;
import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private RouteService routeService;

    private CurrentUser currentUser;

    public RouteController(RouteService routeService, CurrentUser currentUser) {
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel(){

        return new RouteAddBindingModel();
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        RouteDetailsViewModel routeDetailsViewModel = routeService.findRouteById(id);

        model.addAttribute("routeDetailsViewModel", routeDetailsViewModel);

        return "route-details";
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {

        List<RouteViewModel> routeViewModel = routeService.findAllRoutesView();

        model.addAttribute("routes", routeViewModel);

        return "routes";
    }

    @GetMapping("/add")
    public String add() {

        if (currentUser.getUsername() == null) {

            return "redirect:/users/login";
        }

        return "add-route";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid RouteAddBindingModel routeAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {

            redirectAttributes
                .addFlashAttribute("routeAddBindingModel", routeAddBindingModel)
                .addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);

            return "redirect:add";
        }

        routeService.createRoute(routeAddBindingModel);

        return "redirect:all";
    }
}
