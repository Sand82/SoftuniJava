package com.example.spotifyplaylist.services.impl;

import com.example.spotifyplaylist.models.bindings.SongAddBindingModel;
import com.example.spotifyplaylist.models.entities.Song;
import com.example.spotifyplaylist.models.entities.Style;
import com.example.spotifyplaylist.repositories.SongRepository;
import com.example.spotifyplaylist.services.SongService;
import com.example.spotifyplaylist.services.StyleService;
import com.example.spotifyplaylist.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final UserService userService;
    private final StyleService styleService;
    private final ModelMapper mapper;

    public SongServiceImpl(SongRepository songRepository, UserService userService, StyleService styleService, ModelMapper mapper) {
        this.songRepository = songRepository;
        this.userService = userService;
        this.styleService = styleService;
        this.mapper = mapper;
    }

    @Override
    public void createSong(SongAddBindingModel songAddBindingModel) {

        Song song = mapper.map(songAddBindingModel, Song.class);

        Style style = styleService.getStyle(songAddBindingModel.getStyle());

        song.setStyle(style);

        songRepository.save(song);

        userService.saveSong(song);
    }
}
