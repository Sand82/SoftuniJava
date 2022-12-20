package com.example.books.init;

import com.example.books.models.entities.Author;
import com.example.books.models.entities.Book;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInit implements CommandLineRunner {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    public DatabaseInit(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (authorRepository.count() == 0) {

            Author author1 = new Author();
            author1.setName("Arthur Hailey");

            Author author2 = new Author();
            author2.setName("John Grisham");

            Author author3 = new Author();
            author3.setName("Frederick Forsyth");

            authorRepository.saveAll(List.of(author1, author2, author3));
        }

        if (bookRepository.count() == 0) {

            Book book1 = new Book();
            book1.setTitle("Airport");

            Book book2 = new Book();
            book2.setTitle("Hotel");

            Book book3 = new Book();
            book3.setTitle("Wheels");

            Book book4 = new Book();
            book4.setTitle("Detective");

            Book book5 = new Book();
            book5.setTitle("The Firm");

            Book book6 = new Book();
            book6.setTitle("The Client");

            Book book7 = new Book();
            book7.setTitle("The Partner");

            Book book8 = new Book();
            book8.setTitle("The Confession");

            Book book9 = new Book();
            book9.setTitle("The Odessa File");

            Book book10 = new Book();
            book10.setTitle("The Shepherd");

            Book book11 = new Book();
            book11.setTitle("Emeka");

            Book book12 = new Book();
            book12.setTitle("The Deceiver");

            bookRepository.saveAll(List.of(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, book11, book12));
        }
    }
}
