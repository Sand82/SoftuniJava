package com.example.books.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    private boolean isDelete = false;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public Book setDelete(boolean delete) {
        isDelete = delete;
        return this;
    }
}
