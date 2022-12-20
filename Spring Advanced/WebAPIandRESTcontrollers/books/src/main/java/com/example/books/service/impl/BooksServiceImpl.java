package com.example.books.service.impl;

import com.example.books.models.dto.BookDTO;
import com.example.books.models.entities.Author;
import com.example.books.models.entities.Book;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import com.example.books.service.BooksService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BooksServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {

        return bookRepository.findAll().stream().map(b -> {
           BookDTO model = new BookDTO();
           model.setTitle(b.getTitle()).setAuthorName(b.getAuthor().getName());

           return model;

        }).toList();
    }

    @Override
    public BookDTO getBookById(Long id) {

        return bookRepository.findById(id).stream().map(b -> {
            BookDTO model = new BookDTO();
            model.setTitle(b.getTitle());
            model.setAuthorName(b.getAuthor().getName());

            return model;
        }).findFirst().orElse(null);
    }

    @Override
    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id).get();

        bookRepository.delete(book);
    }

    @Override
    public long createBook(BookDTO model) {

        Book book = new Book();

        book.setTitle(model.getTitle());

        Optional<Author> author = authorRepository.findByName(model.getAuthorName());


        if (author == null) {

            Author createAuthor = new Author();
            createAuthor.setName(model.getAuthorName());
            authorRepository.save(createAuthor);

            setAuthorBook(book, createAuthor);
        } else {

            setAuthorBook(book, author.get());
        }

        Book createBook = bookRepository.saveAndFlush(book);

        return createBook.getId();
    }

    private void setAuthorBook(Book book , Author author) {

        book.setAuthor(author);
    }
}
