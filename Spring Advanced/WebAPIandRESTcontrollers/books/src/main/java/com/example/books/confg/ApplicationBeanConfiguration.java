package com.example.books.confg;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.NoRepositoryBean;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper ModelMapper() {

       return new ModelMapper();
    }
}
