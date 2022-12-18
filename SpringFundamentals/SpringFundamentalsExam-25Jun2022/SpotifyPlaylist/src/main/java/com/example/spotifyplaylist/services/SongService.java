package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.models.bindings.SongAddBindingModel;
import com.example.spotifyplaylist.models.views.AllSongViewModel;

import java.util.List;

public interface SongService {
    void createSong(SongAddBindingModel songAddBindingModel);

    List<AllSongViewModel> getAllSongs();
}
