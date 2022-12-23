package com.example.mobilelele.services.impl;

import com.example.mobilelele.model.binding.CarAddBindingModel;
import com.example.mobilelele.model.binding.OfferUpdateBindingModel;
import com.example.mobilelele.model.entities.ModelEntity;
import com.example.mobilelele.model.entities.OfferEntity;
import com.example.mobilelele.model.entities.UserEntity;
import com.example.mobilelele.model.services.OfferUpdateServiceModel;
import com.example.mobilelele.model.view.AddModelViewModel;
import com.example.mobilelele.model.view.OfferDetailsViewModel;
import com.example.mobilelele.model.view.OfferSummeryViewModel;
import com.example.mobilelele.repositories.ModelRepository;
import com.example.mobilelele.repositories.OfferRepository;

import com.example.mobilelele.services.ModelService;
import com.example.mobilelele.services.OfferService;
import com.example.mobilelele.services.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper mapper;
    private final ModelRepository modelRepository;
    private final ModelService modelService;

    private final UserService userService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository,
                            ModelMapper mapper,
                            ModelRepository modelRepository,
                            ModelService modelService,
                            UserService userService) {

        this.offerRepository = offerRepository;
        this.mapper = mapper;
        this.modelRepository = modelRepository;
        this.modelService = modelService;
        this.userService = userService;
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

        OfferEntity offerEntity = offerRepository.findById(offerModel.getId()).orElse(null);

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

    @Override
    public List<AddModelViewModel> getModels() {

        List<AddModelViewModel> models = modelRepository
                .findAllDistinctBy()
                .stream().map(o -> mapper.map(o, AddModelViewModel.class))
                .toList();

        return models;
    }

    @Override
    public void createOffer(CarAddBindingModel carAddBindingModel, Principal principal) {

        OfferEntity offer = mapper.map(carAddBindingModel, OfferEntity.class);

        ModelEntity model = modelService.getByName(carAddBindingModel.getModel());

        UserEntity user = userService.getByUsername(principal.getName());

        offer.setModel(model);
        offer.setSeller(user);

        offerRepository.save(offer);

    }

    private OfferUpdateServiceModel createOfferUpdateModel(OfferUpdateBindingModel offerModel) {

        OfferUpdateServiceModel model = mapper.map(offerModel, OfferUpdateServiceModel.class);

        return model;
    }

    @Override
    public OfferDetailsViewModel findById(Long id, String username) {

        OfferEntity offerEntity = offerRepository.findById(id).get();

        OfferDetailsViewModel model = mapDetailsView(offerEntity);

        UserEntity user = userService.getByUsername(username);

        model.setSellerFirstName(user.getFirstName());
        model.setSellerLastName(user.getLastName());

        return model;
    }

    private OfferDetailsViewModel mapDetailsView(OfferEntity offerEntity) {

        OfferDetailsViewModel model = mapper.map(offerEntity, OfferDetailsViewModel.class);

        return model;
    }
}
