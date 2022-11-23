package com.example.gamestore.services;

import com.example.gamestore.entities.Game;
import com.example.gamestore.entities.games.AddGameDTO;

public interface GameService {

    String addGame(AddGameDTO model);

    String editGame();

    String deleteGame();
}
