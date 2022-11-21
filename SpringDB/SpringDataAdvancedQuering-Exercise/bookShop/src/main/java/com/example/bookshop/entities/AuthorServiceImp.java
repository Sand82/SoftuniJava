package com.example.bookshop.entities;

import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorServiceImp implements AuthorService {


    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImp(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {

        int size = (int) this.authorRepository.count();

        int authorId = new Random().nextInt(size + 1);

        Author author = this.authorRepository.findById(authorId).orElse(null);

        if (author == null) {

            getRandomAuthor();
        }

        return author;
    }
}
