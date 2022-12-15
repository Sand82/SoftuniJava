package com.example.likebook.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    private ModelMapper ModelMapper() {
        return new ModelMapper();
    }
}
