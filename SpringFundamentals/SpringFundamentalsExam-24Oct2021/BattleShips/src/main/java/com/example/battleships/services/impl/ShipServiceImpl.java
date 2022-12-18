package com.example.battleships.services.impl;

import com.example.battleships.models.bindings.AddShipBindingModel;
import com.example.battleships.models.entities.Category;
import com.example.battleships.models.entities.Ship;
import com.example.battleships.models.entities.User;
import com.example.battleships.repositories.ShipRepository;
import com.example.battleships.security.CurrentUser;
import com.example.battleships.services.CategoryService;
import com.example.battleships.services.ShipService;
import com.example.battleships.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ShipServiceImpl implements ShipService {

    private final UserService userService;
    private final CategoryService categoryService;
    private final CurrentUser currentUser;
    private final ModelMapper mapper;
    private final ShipRepository shipRepository;

    @Autowired
    public ShipServiceImpl( UserService userService, CategoryService categoryService, CurrentUser currentUser, ModelMapper mapper, ShipRepository shipRepository) {

        this.userService = userService;
        this.categoryService = categoryService;
        this.currentUser = currentUser;
        this.mapper = mapper;
        this.shipRepository = shipRepository;
    }

    @Override
    public void createShip(AddShipBindingModel addShipBindingModel) {

        User user = userService.getById();

        Category category = categoryService.getCategoryByCategoryEnum(addShipBindingModel.getCategory());

        Ship ship = mapper.map(addShipBindingModel, Ship.class);

        ship.setOwner(user);
        ship.setCategory(category);

        shipRepository.save(ship);
    }
}
