package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.models.bindings.SongAddBindingModel;

public interface SongService {
    void createSong(SongAddBindingModel songAddBindingModel);
}
