package com.example.pathfinder.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "massages")
public class Massage extends BaseEntity {

    @Column(name = "date_time", nullable = false)
    private LocalDate dateTime;

    @Lob
    @Column(nullable = false)
    private LocalDate textContent;

    @ManyToOne
    private User author;

    @ManyToOne
    private User recipient;

    public Massage() {
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getTextContent() {
        return textContent;
    }

    public void setTextContent(LocalDate textContent) {
        this.textContent = textContent;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}

