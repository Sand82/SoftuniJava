package com.example.bookshop.repositories;

import com.example.bookshop.dataTransferObjects.AuthorsBooksCopiesDTO;
import com.example.bookshop.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findByFirstNameEndingWith(String input);

    @Query("SELECT a.firstName AS firstName," +
            " a.lastName AS lastName," +
            " SUM(b.copies) AS totalCopies" +
            " FROM authors a" +
            " JOIN a.books AS b" +
            " GROUP BY b.author" +
            " ORDER BY totalCopies DESC")
    List<AuthorsBooksCopiesDTO> findByAuthorByCopiesBooks();
}
