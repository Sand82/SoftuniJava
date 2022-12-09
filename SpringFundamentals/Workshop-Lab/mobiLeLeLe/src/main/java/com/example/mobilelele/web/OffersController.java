package com.example.mobilelele.web;

import com.example.mobilelele.repositories.OfferRepository;
import com.example.mobilelele.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final OfferService offerService;

    public OffersController(OfferService offerService,
                            OfferRepository offerRepository) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public String getAllOffers(Model model) {

        model.addAttribute("offers", offerService.getAllOffers());

        return "offers";
    }

    @GetMapping("/{id}/details") //Details Get
    public String showOffer(@PathVariable Long id, Model model) {

        model.addAttribute("offer", offerService.findById(id));

        return "details";
    }

    @DeleteMapping("offers/{id}") //Details Delete
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model) {
        model.addAttribute("edit");

        return "update";
    }
}
