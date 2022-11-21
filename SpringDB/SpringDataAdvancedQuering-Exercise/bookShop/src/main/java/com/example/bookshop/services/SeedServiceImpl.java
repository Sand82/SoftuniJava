package com.example.bookshop.services;

import com.example.bookshop.entities.*;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class SeedServiceImpl implements SeedService {

    private static final String RESOURCE_PATH = "bookShop/src/main/resources/files";
    private static final String AUTHORS_FILENAME_PATH = RESOURCE_PATH + "/authors.txt";
    private static final String CATEGORY_FILENAME_PATH = RESOURCE_PATH + "/categories.txt";

    private static final String BOOKS_FILENAME_PATH = RESOURCE_PATH + "/books.txt";

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository booksRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;

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
    public void seedBooks() throws IOException {

        Files.readAllLines(Path.of(BOOKS_FILENAME_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::getBookObject)
                .forEach(booksRepository::save);
    }

    private Book getBookObject(String line) {

        String[] bookParts = line.split("\\s+");

        int i = new Random().nextInt(3);
        EditionType editionType = EditionType.values()[i];

        LocalDate publishedDate = LocalDate.parse(bookParts[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        int copies = Integer.parseInt(bookParts[2]);

        BigDecimal price = new BigDecimal(bookParts[3]);

        AgeRestriction ageRestrictions = AgeRestriction.values()[Integer.parseInt(bookParts[4])];

        String title = Arrays.stream(bookParts).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();

        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(title, editionType, price, copies, publishedDate, author, categories, ageRestrictions);
    }
}
