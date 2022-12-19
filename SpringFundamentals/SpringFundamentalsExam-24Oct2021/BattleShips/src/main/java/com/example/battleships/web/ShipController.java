package com.example.battleships.web;

import com.example.battleships.models.bindings.AddShipBindingModel;
import com.example.battleships.models.bindings.FightShipsBindingModel;
import com.example.battleships.services.ShipService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @PostMapping("/fight")
    public String fightResult(FightShipsBindingModel fightShipsBindingModel) {

        shipService.setBattle(fightShipsBindingModel);

        return "redirect:/";
    }

    @GetMapping("/add")
    public String add(){

        return "ship-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddShipBindingModel addShipBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasFieldErrors()) {

            redirectAttributes.addFlashAttribute("addShipBindingModel", addShipBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addShipBindingModel", bindingResult);

            return "redirect:add";
        }

        shipService.createShip(addShipBindingModel);

        return "redirect:/";
    }

    @ModelAttribute
    public FightShipsBindingModel fightShipsBindingModel() {

        return new FightShipsBindingModel();
    }

    @ModelAttribute
    public AddShipBindingModel addShipBindingModel() {

       return new AddShipBindingModel();
    }
}
