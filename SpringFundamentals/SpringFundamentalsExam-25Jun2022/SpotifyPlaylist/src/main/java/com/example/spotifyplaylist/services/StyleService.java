package com.example.spotifyplaylist.services;

import com.example.spotifyplaylist.models.entities.Style;
import com.example.spotifyplaylist.models.entities.enums.StyleEnum;

public interface StyleService {
    void createStyles();

    Style getStyle(StyleEnum style);
}
