package com.example.books.models.dto;

public class BookDTO {

    private String title;
    private String authorName;

    public BookDTO() {
    }

    public String getTitle() {
        return title;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }
}
