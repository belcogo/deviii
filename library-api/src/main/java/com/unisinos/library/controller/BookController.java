package com.unisinos.library.controller;

import com.unisinos.library.service.BookService;
import com.unisinos.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> listBooks(@RequestParam(name = "title", required = false) String title,
                                       @RequestParam(name = "genre", required = false) String genre) {
        var books = bookService.getBookByParams(title, genre);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/users/{id}/books")
    public ResponseEntity<?> getBooksFromUser(@PathVariable("id") Long userId) {
        var books = bookService.getBooksByUser(userId);
        return ResponseEntity.ok(books);
    }
}
