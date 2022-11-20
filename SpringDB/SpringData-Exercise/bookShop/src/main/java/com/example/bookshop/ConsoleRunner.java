package com.example.bookshop;

import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.Book;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

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
    public void run(String... args) throws Exception {
        //seedService.seedAuthors();
        //seedService.seedCategories();
        //seedService.seedBooks();

        //seedService.seedAll();

        //this._01_booksAfter2000();
        //this._02_allAuthorsWithBookBefore1990();
        this._03_allAuthorsOrderedByBookCount();
    }

    private void _03_allAuthorsOrderedByBookCount() {


        List<Author> authors =this.authorRepository.findAll();

        authors.stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " -> " + a.getBooks().size()));
    }

    private void _02_allAuthorsWithBookBefore1990() {

        LocalDate before1990 = LocalDate.of(1990, 1, 01);

        List<Author> authors = authorRepository.findDistinctByBooksReleaseDateBefore(before1990);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void _01_booksAfter2000() {

        LocalDate year2000 = LocalDate.of(1999, 12, 31);

        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);

        books.forEach(b -> System.out.println(b.getReleaseDate() + " " + b.getTitle()));

        int countOfBooks = this.bookRepository.countByReleaseDateAfter(year2000);

        System.out.println("Total count: " + countOfBooks);
    }
}
