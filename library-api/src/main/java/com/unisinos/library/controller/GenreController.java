package com.unisinos.library.controller;

import com.unisinos.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping
    public ResponseEntity<?> listGenres() {
        return ResponseEntity.ok(genreRepository.findAll());
    }
}
