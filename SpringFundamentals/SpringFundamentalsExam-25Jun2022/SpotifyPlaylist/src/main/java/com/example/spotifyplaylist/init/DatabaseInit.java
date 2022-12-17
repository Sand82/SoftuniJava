package com.example.spotifyplaylist.init;

import com.example.spotifyplaylist.services.StyleService;
import org.hibernate.annotations.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private StyleService styleService;

    public DatabaseInit(StyleService styleService) {
        this.styleService = styleService;
    }

    @Override
    public void run(String... args) throws Exception {

        styleService.createStyles();
    }
}
