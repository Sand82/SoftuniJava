package com.example.mobilelele.services;

import com.example.mobilelele.model.view.OfferSummeryViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferService {

    List<OfferSummeryViewModel> getAllOffers();
}
