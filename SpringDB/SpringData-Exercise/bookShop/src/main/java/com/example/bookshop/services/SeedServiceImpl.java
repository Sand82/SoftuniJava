package com.example.bookshop.services;

import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.Category;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class SeedServiceImpl implements SeedService {

    private static final String RESOURCE_PATH = "src/main/resources/files";
    private static final String AUTHORS_FILENAME_PATH = RESOURCE_PATH + "/authors.txt";
    private static final String CATEGORY_FILENAME_PATH = RESOURCE_PATH + "/categories.txt";

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void seedAuthors() throws IOException {

        Files.readAllLines(Path.of(AUTHORS_FILENAME_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> s.split(" "))
                .map(n -> new Author(n[0], n[1]))
                .forEach(authorRepository::save);
    }

    @Override
    public void seedCategories() throws IOException {

        Files.readAllLines(Path.of(CATEGORY_FILENAME_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedBooks() {

    }
}
