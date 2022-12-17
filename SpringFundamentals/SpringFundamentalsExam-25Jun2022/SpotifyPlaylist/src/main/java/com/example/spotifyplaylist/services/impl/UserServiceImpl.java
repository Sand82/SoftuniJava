package com.example.spotifyplaylist.services.impl;

import com.example.spotifyplaylist.models.bindings.UserRegisterBindingModel;
import com.example.spotifyplaylist.models.entities.Song;
import com.example.spotifyplaylist.models.entities.User;
import com.example.spotifyplaylist.repositories.UserRepository;
import com.example.spotifyplaylist.services.UserService;
import com.example.spotifyplaylist.seurity.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final ModelMapper mapper;
    public final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean getByUsernameOrEmail(String username, String email) {

        User user = userRepository.findByUsernameOrEmail(username, email);


        if (user == null) {

            return false;
        }

        return true;
    }

    @Override
    public void createUser(UserRegisterBindingModel userRegisterBindingModel) {

        User user = mapper.map(userRegisterBindingModel, User.class);

        userRepository.save(user);
    }

    @Override

    public boolean getByUsernameAndPassword(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) {

            return false;
        }

        currentUser.setId(user.getId());
        currentUser.setUsername(username);

        return true;
    }

    @Override
    public void logout() {

        currentUser.setId(null);
        currentUser.setUsername(null);
    }

    @Override
    public void saveSong(Song song) {

        User user = userRepository.findById(currentUser.getId()).orElse(null);

        user.addSong(song);

        userRepository.save(user);
    }
}
