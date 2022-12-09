package com.example.mobilelele.services.impl;

import com.example.mobilelele.model.entities.OfferEntity;
import com.example.mobilelele.model.view.OfferSummeryViewModel;
import com.example.mobilelele.repositories.OfferRepository;
import com.example.mobilelele.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;

    private ModelMapper mapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper mapper) {

        this.offerRepository = offerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<OfferSummeryViewModel> getAllOffers() {

        List<OfferEntity> offers = offerRepository.findAll();

        List<OfferSummeryViewModel> models = offers.stream().map(o -> mapper.map(o, OfferSummeryViewModel.class)).toList();

        return models;
    }
}
