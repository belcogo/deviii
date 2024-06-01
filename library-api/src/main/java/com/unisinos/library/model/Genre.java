package com.unisinos.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Genre {
    @Id
    public Long id;

    @Column(nullable = false)
    public String name;
}
