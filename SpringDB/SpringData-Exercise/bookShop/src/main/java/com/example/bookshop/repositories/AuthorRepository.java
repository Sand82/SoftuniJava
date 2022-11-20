package com.example.bookshop.repositories;

import com.example.bookshop.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);

    List<Author> findAll();

    Author getAuthorByFirstNameAndLastName(String firstName, String lastName);
}
