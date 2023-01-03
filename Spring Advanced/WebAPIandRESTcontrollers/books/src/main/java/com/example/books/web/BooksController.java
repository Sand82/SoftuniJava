package com.example.books.web;

import com.example.books.models.dto.BookDTO;
import com.example.books.service.BooksService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks() {

        List<BookDTO> allBooks = booksService.getAllBooks();

        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookDyId(@PathVariable Long id) {

        BookDTO model = booksService.getBookById(id);

        if (model == null) {

            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.ok(model);
        }
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<BookDTO>> getAllBooks(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {

        Page<BookDTO> models = booksService.getBooks(pageNo, pageSize, sortBy);

        return ResponseEntity.ok(models);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {

        booksService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO book, UriComponentsBuilder builder) {

        long bookId = booksService.createBook(book);

        URI location = builder.path("/books/{id}")
                .buildAndExpand(bookId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookDTO book) {

        throw new UnsupportedOperationException("Comming soon!");
    }
}

