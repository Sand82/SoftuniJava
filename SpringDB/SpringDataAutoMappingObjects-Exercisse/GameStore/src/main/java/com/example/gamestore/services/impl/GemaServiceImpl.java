package com.example.gamestore.services.impl;

import com.example.gamestore.UserState;
import com.example.gamestore.entities.Game;
import com.example.gamestore.entities.games.AddGameDTO;
import com.example.gamestore.repositories.GameRepository;
import com.example.gamestore.repositories.UserRepository;
import com.example.gamestore.services.GameService;
import com.example.gamestore.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
    public String addGame(AddGameDTO model) {

        if ( UserState.user == null || !UserState.user.isAdmin()) {
            return "Mist authorization.";
        }

        ModelMapper mapper = new ModelMapper();
        TypeMap<AddGameDTO, Game> typeMapper = mapper.createTypeMap(AddGameDTO.class, Game.class)
                .addMappings(m -> m.map(AddGameDTO::getPrice, Game::setPrice));

        game = typeMapper.map(model);


        gameRepository.save(game);

        return String.format("Successfully added the game %s.", game.getTitle());
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
