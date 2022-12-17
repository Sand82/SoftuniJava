package com.example.spotifyplaylist.services.impl;

import com.example.spotifyplaylist.models.entities.Style;
import com.example.spotifyplaylist.models.entities.enums.StyleEnum;
import com.example.spotifyplaylist.repositories.StyleRepository;
import com.example.spotifyplaylist.services.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {

    private StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void createStyles() {

        if (styleRepository.count() == 0) {

            List<Style> styles = Arrays.stream(StyleEnum.values()).map(c -> {
                Style style = new Style();
                style.setName(c);
                return style;
            }).toList();

            styleRepository.saveAll(styles);
        }
    }
}
