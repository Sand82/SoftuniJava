package com.example.bookshop;

import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.EditionType;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;

    private AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        //seedService.seedAuthors();
        //seedService.seedCategories();
        //seedService.seedBooks();
        //seedService.seedAll();

        //this._01_BooksTitlesByAgeRestriction(scanner);
        //this._02_GoldenBooks();
    }

    private void _02_GoldenBooks() {

        EditionType editionType =  EditionType.GOLD;

        int copies = 5000;

        List<Book> books = bookRepository.findByEditionTypeAndCopiesLessThan(editionType, copies);

        printBooks(books);
    }

    private void _01_BooksTitlesByAgeRestriction(Scanner scanner) {

        String ageRestrictionInput = scanner.nextLine();

        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionInput.toUpperCase());

        List<Book> books = bookRepository.findByAgeRestriction(ageRestriction);

        printBooks(books);
    }

    private static void printBooks(List<Book> books) {
        books.forEach(b -> System.out.println(b.getTitle()));
    }
}
