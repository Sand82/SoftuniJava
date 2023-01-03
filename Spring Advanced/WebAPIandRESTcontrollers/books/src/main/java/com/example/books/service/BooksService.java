package com.example.books.service;

import com.example.books.models.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BooksService {
    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    void deleteBook(Long id);

    long createBook(BookDTO book);

    Page<BookDTO> getBooks(Integer pageNo, Integer pageSize, String sortBy);
}
