package com.example.battleships.services.impl;

import com.example.battleships.models.bindings.AddShipBindingModel;
import com.example.battleships.models.bindings.FightShipsBindingModel;
import com.example.battleships.models.entities.Category;
import com.example.battleships.models.entities.Ship;
import com.example.battleships.models.entities.User;
import com.example.battleships.models.views.AllShipsViewModel;
import com.example.battleships.models.views.ShipViewModel;
import com.example.battleships.repositories.ShipRepository;
import com.example.battleships.security.CurrentUser;
import com.example.battleships.services.CategoryService;
import com.example.battleships.services.ShipService;
import com.example.battleships.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Override
    public List<ShipViewModel> setAttackers() {

        List<Ship> ships = getAll();

        List<ShipViewModel> model = ships.stream()
                .filter(s -> Objects.equals(s.getOwner().getId(), currentUser.getId()))
                .map(s -> mapper.map(s, ShipViewModel.class)).toList();

        return model;
    }

    @Override
    public List<ShipViewModel> setDefenders() {

        List<Ship> ships = getAll();

        List<ShipViewModel> model = ships.stream()
                .filter(s -> s.getOwner().getId() != currentUser.getId())
                .map(s -> mapper.map(s, ShipViewModel.class)).toList();

        return model;
    }

    @Override
    public void setBattle(FightShipsBindingModel fightShipsBindingModel) {

        Ship attacker = getByName(fightShipsBindingModel.getAttacker());

        Ship defender = getByName(fightShipsBindingModel.getDefender());

        defender.setHealth(defender.getHealth() - attacker.getPower());

        attacker.setHealth(attacker.getHealth() - defender.getPower());

        setInDataBase(attacker);

        setInDataBase(defender);
    }

    @Override
    public List<AllShipsViewModel> getAllModels() {

        List<AllShipsViewModel> models = getAll().stream()
                .map(s -> mapper.map(s, AllShipsViewModel.class))
                .toList();

        return models;
    }

    private List<Ship> getAll() {

        return shipRepository.findAll();
    }

    private void setInDataBase(Ship ship) {

        if (ship.getHealth() <= 0) {

            shipRepository.delete(ship);
        } else {

            shipRepository.save(ship);
        }
    }

    private Ship getByName(String name) {

        return shipRepository.findByName(name);
    }
}
