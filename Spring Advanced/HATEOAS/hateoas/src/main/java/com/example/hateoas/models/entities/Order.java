package com.example.hateoas.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Course getCourse() {
        return course;
    }

    public Order setCourse(Course course) {
        this.course = course;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public Order setStudent(Student student) {
        this.student = student;
        return this;
    }
}
