package com.example.spotifyplaylist.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper ModelMapper() {

        return new ModelMapper();
    }
}
