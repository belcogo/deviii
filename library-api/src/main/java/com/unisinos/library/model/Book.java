package com.unisinos.library.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String author;

    @Column(nullable = false)
    public LocalDate publishedDate;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public ZonedDateTime createdDateTime;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    public User owner;
}
