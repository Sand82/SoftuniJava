package com.example.bookshop;

import com.example.bookshop.entities.Book;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;

    private final BookRepository bookRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //seedService.seedAuthors();
        //seedService.seedCategories();
        //seedService.seedBooks();

        //seedService.seedAll();

        this._01_booksAfter2000();
    }

    private void _01_booksAfter2000() {

        LocalDate year2000 = LocalDate.of(2000, 1, 1);

        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);

        System.out.println(books.size());

        books.forEach(b -> System.out.println(b.getReleaseDate() + " " + b.getTitle()));
    }
}
