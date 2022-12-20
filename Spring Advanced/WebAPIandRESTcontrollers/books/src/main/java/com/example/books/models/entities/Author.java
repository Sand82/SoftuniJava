package com.example.books.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public void addBook(Book book){
        books.add(book);
    }

    public Author() {
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Author setBooks(List<Book> books) {
        this.books = books;
        return this;
    }
}
