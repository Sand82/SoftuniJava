package com.example.gamestore.services.impl;

import com.example.gamestore.entities.Game;
import com.example.gamestore.entities.games.AddGameDTO;
import com.example.gamestore.repositories.GameRepository;
import com.example.gamestore.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GemaServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GemaServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    private Game game = null;
    @Override
    public Game addGame(AddGameDTO model) {

        ModelMapper mapper = new ModelMapper();

        game = mapper.map(model, Game.class);

        return gameRepository.save(game);
    }

    @Override
    public String editGame() {
        return null;
    }

    @Override
    public String deleteGame() {
        return null;
    }
}
