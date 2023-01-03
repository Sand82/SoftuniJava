package com.example.books.web;

import com.example.books.models.dto.BookDTO;
import com.example.books.service.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class BooksController {

    private BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {

        List<BookDTO> allBooks = booksService.getAllBooks();

        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> getBookDyId(@PathVariable Long id) {

        BookDTO model = booksService.getBookById(id);

        if (model == null) {

            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.ok(model);
        }
    }

    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {

        booksService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/books")
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO book, UriComponentsBuilder builder) {

      long bookId = booksService.createBook(book);

        URI location = builder.path("/books/{id}")
                .buildAndExpand(bookId).toUri();

      return ResponseEntity.created(location).build();
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookDTO book) {

        throw new UnsupportedOperationException("Comming soon!");
    }
}

