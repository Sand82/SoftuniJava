package com.example.mobilelele.services;

import com.example.mobilelele.model.binding.CarAddBindingModel;
import com.example.mobilelele.model.binding.OfferUpdateBindingModel;
import com.example.mobilelele.model.view.AddModelViewModel;
import com.example.mobilelele.model.view.OfferDetailsViewModel;
import com.example.mobilelele.model.view.OfferSummeryViewModel;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface OfferService {
    List<OfferSummeryViewModel> getAllOffers();
    OfferDetailsViewModel findById(Long id, String username);
    void deleteOffer(Long id);
    void updateOffer( OfferUpdateBindingModel model);
    OfferUpdateBindingModel createOfferUpdateModel(Long id, OfferDetailsViewModel offerDetailsViewModel);
    List<AddModelViewModel> getModels();
    void createOffer(CarAddBindingModel carAddBindingModel, Principal principal);
    boolean isOwner(String username, Long id);
}
