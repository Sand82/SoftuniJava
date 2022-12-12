package com.example.mobilelele.web;

import com.example.mobilelele.model.binding.OfferUpdateBindingModel;
import com.example.mobilelele.model.entities.enums.EngineEnum;
import com.example.mobilelele.model.entities.enums.TransmissionEnum;
import com.example.mobilelele.model.view.OfferDetailsViewModel;
import com.example.mobilelele.repositories.OfferRepository;
import com.example.mobilelele.services.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @DeleteMapping("/{id}") //Details Delete
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/{id}/edit") //Edit
    public String editOffer(@PathVariable Long id, Model model) {

        OfferDetailsViewModel offerDetailsViewModel = offerService.findById(id);

        OfferUpdateBindingModel offerModel = offerService.createOfferUpdateModel(id, offerDetailsViewModel);

        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        model.addAttribute("offerModel", offerModel);

        return "update";
    }

    @GetMapping("/{id}/edit/errors") //Edit errors
    public String editOfferErrors(@PathVariable Long id, Model model) {

        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());

        return "update";
    }

    @PatchMapping("/{id}/edit") //Edit
    public String editOffer(@PathVariable Long id,
                            @Valid OfferUpdateBindingModel offerModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/" + id + "/edit/errors";
        }

        offerModel.setId(id);

        offerService.updateOffer(offerModel);

        return "redirect:/offers/" + id + "/details";
    }
}