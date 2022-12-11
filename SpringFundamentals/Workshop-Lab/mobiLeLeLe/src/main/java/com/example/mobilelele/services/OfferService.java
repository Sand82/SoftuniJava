package com.example.mobilelele.services;

import com.example.mobilelele.model.binding.OfferUpdateBindingModel;
import com.example.mobilelele.model.view.OfferDetailsViewModel;
import com.example.mobilelele.model.view.OfferSummeryViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferService {
    List<OfferSummeryViewModel> getAllOffers();
    OfferDetailsViewModel findById(Long id);
    void deleteOffer(Long id);
    void updateOffer( OfferUpdateBindingModel model);
    OfferUpdateBindingModel createOfferUpdateModel(Long id, OfferDetailsViewModel offerDetailsViewModel);
}
