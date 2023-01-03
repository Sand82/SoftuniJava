package com.example.books.service.impl;

import com.example.books.models.dto.BookDTO;
import com.example.books.models.entities.Author;
import com.example.books.models.entities.Book;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import com.example.books.service.BooksService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements BooksService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private ModelMapper mapper;

    public BooksServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BookDTO> getAllBooks() {

        return bookRepository.findAll().stream().filter(b -> !b.isDelete()).map(b -> {
           BookDTO model = new BookDTO();
           model.setTitle(b.getTitle()).setAuthorName(b.getAuthor().getName()).setId(b.getId());

           return model;

        }).toList();
    }

    @Override
    public BookDTO getBookById(Long id) {

        return bookRepository.findById(id).stream().filter(b -> !b.isDelete()).map(b -> {
            BookDTO model = new BookDTO();
            model.setTitle(b.getTitle());
            model.setAuthorName(b.getAuthor().getName()).setId(b.getId());

            return model;
        }).findFirst().orElse(null);
    }

    @Override
    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id).get();

        book.setDelete(true);

        bookRepository.save(book);
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

    @Override
    public Page<BookDTO> getBooks(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        List<BookDTO> models = bookRepository.findAll(pageable).stream().map(b -> mapper.map(b, BookDTO.class)).toList();

        Page<BookDTO> pages = new PageImpl<>(models);

        return pages;
    }

    private void setAuthorBook(Book book , Author author) {

        book.setAuthor(author);
    }
}
