package com.example.battleships.services;

import com.example.battleships.models.bindings.AddShipBindingModel;
import com.example.battleships.models.bindings.FightShipsBindingModel;
import com.example.battleships.models.views.AllShipsViewModel;
import com.example.battleships.models.views.ShipViewModel;

import java.util.List;

public interface ShipService {
    void createShip(AddShipBindingModel addShipBindingModel);

    List<ShipViewModel> setAttackers();

    List<ShipViewModel> setDefenders();

    void setBattle(FightShipsBindingModel fightShipsBindingModel);

    List<AllShipsViewModel> getAllModels();
}
