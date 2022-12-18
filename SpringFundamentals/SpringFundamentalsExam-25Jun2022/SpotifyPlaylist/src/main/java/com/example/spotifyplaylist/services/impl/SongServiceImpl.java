package com.example.spotifyplaylist.services.impl;

import com.example.spotifyplaylist.models.bindings.SongAddBindingModel;
import com.example.spotifyplaylist.models.entities.Song;
import com.example.spotifyplaylist.models.entities.Style;
import com.example.spotifyplaylist.models.entities.User;
import com.example.spotifyplaylist.models.views.AllSongViewModel;
import com.example.spotifyplaylist.repositories.SongRepository;
import com.example.spotifyplaylist.repositories.UserRepository;
import com.example.spotifyplaylist.services.SongService;
import com.example.spotifyplaylist.services.StyleService;
import com.example.spotifyplaylist.services.UserService;
import com.example.spotifyplaylist.seurity.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final StyleService styleService;
    private final ModelMapper mapper;
    private final CurrentUser currentUser;

    public SongServiceImpl(SongRepository songRepository, UserRepository userRepository, @Lazy UserService userService, StyleService styleService, ModelMapper mapper, CurrentUser currentUser) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.styleService = styleService;
        this.mapper = mapper;
        this.currentUser = currentUser;
    }

    @Override
    public void createSong(SongAddBindingModel songAddBindingModel) {

        Song song = mapper.map(songAddBindingModel, Song.class);

        Style style = styleService.getStyle(songAddBindingModel.getStyle());

        song.setStyle(style);

        songRepository.save(song);

        userService.saveSong(song);
    }

    @Override
    public List<AllSongViewModel> getAllSongs() {

        List<AllSongViewModel> models = songRepository
                .findAll().stream()
                .map(s -> mapper.map(s, AllSongViewModel.class)).toList();

        return models;
    }

    @Override
    public void removeAllSongs() {

        User user = userService.getUser();

        for (Song song : user.getPlayList()) {

            song.setDelete(true);

            songRepository.save(song);
        }
    }

    @Override
    public void addSong(Long id) {

        User user = userService.getUser();

        Song song = songRepository.findById(id).orElse(null);

        Song currSong = user.getPlayList().stream()
                .filter(s -> s.getPerformer().equals(song.getPerformer()) && s.getTitle().equals(song.getTitle()) && s.isDelete())
                .findFirst().orElse(null);

        if (currSong == null) {

            user.getPlayList().add(song);
        } else {

            currSong.setDelete(false);
        }

        userRepository.save(user);
    }
}
