package com.example.mobilelele.services.impl;

import com.example.mobilelele.model.binding.OfferUpdateBindingModel;
import com.example.mobilelele.model.entities.OfferEntity;
import com.example.mobilelele.model.services.OfferUpdateServiceModel;
import com.example.mobilelele.model.view.OfferDetailsViewModel;
import com.example.mobilelele.model.view.OfferSummeryViewModel;
import com.example.mobilelele.repositories.OfferRepository;
import com.example.mobilelele.services.OfferService;
import com.example.mobilelele.web.excptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteOffer(Long id) {

        offerRepository.deleteById(id);
    }

    @Override
    public void updateOffer(OfferUpdateBindingModel offerModel) {

        OfferUpdateServiceModel model = createOfferUpdateModel(offerModel);

        OfferEntity offerEntity = offerRepository.findById(offerModel.getId()).orElseThrow(
                () -> new ObjectNotFoundException("Offer whit id " + offerModel.getId() + " don't exist."));

        offerEntity.setMileage(model.getMileage())
                .setPrice(model.getPrice())
                .setEngine(model.getEngine())
                .setTransmission(model.getTransmission())
                .setYear(model.getYear())
                .setDescription(model.getDescription())
                .setImageUrl(model.getImageUrl());

        offerRepository.save(offerEntity);
    }

    @Override
    public OfferUpdateBindingModel createOfferUpdateModel(Long id, OfferDetailsViewModel model) {

        OfferUpdateBindingModel offerUpdateBindingModel = mapper.map(model, OfferUpdateBindingModel.class);

        return offerUpdateBindingModel;
    }

    private OfferUpdateServiceModel createOfferUpdateModel(OfferUpdateBindingModel offerModel) {

        OfferUpdateServiceModel model = mapper.map(offerModel, OfferUpdateServiceModel.class);

        return model;
    }

    @Override
    public OfferDetailsViewModel findById(Long id) {

        OfferEntity offerEntity = offerRepository.findById(id).get();

        OfferDetailsViewModel model = mapDetailsView(offerEntity);

        model.setSellerFirstName("Sand");
        model.setSellerLastName("Stef");

        return model;
    }

    private OfferDetailsViewModel mapDetailsView(OfferEntity offerEntity) {

        OfferDetailsViewModel model = mapper.map(offerEntity, OfferDetailsViewModel.class);

        return model;
    }
}
