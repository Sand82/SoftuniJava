package com.example.mobilelele;

import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@SpringBootApplication
public class MobiLeLeLeApplication {


    public static void main(String[] args) {

        SpringApplication.run(MobiLeLeLeApplication.class, args);
    }
}

