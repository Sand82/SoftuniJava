package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.models.bindings.SongAddBindingModel;
import com.example.spotifyplaylist.models.entities.Song;
import com.example.spotifyplaylist.models.entities.User;
import com.example.spotifyplaylist.models.views.AllSongViewModel;

import java.util.List;

public interface SongService {
    void createSong(SongAddBindingModel songAddBindingModel);

    List<AllSongViewModel> getAllSongs();

    void removeAllSongs();

    void addSong(Long id);
}
