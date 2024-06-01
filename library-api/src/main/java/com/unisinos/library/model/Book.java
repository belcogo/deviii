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

    @ManyToOne
    @JoinColumn(name = "genre_id")
    public Genre genre;

    @Column(name = "published_date", nullable = false)
    public LocalDate publishedDate;

    @CreationTimestamp
    @Column(name = "created_date_time", nullable = false, updatable = false)
    public ZonedDateTime createdDateTime;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    public User owner;

    @Column(name="external_photo_path")
    public String externalPhotoPath;
}
