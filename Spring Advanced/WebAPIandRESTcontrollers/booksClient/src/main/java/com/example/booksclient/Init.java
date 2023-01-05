package com.example.booksclient;

import com.example.booksclient.model.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;

@Component
public class Init implements CommandLineRunner {

    private RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(Init.class);

    public Init(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {       

        ResponseEntity<BookDTO[]> booksResponse = restTemplate.getForEntity("http://localhost:8080/books", BookDTO[].class);

        if (booksResponse.hasBody()) {
            BookDTO[] books = booksResponse.getBody();

            for (BookDTO book : books) {
                LOGGER.info("A book that came to the server {}", book);
            }
        }
    }
}
