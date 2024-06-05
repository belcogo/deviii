package com.unisinos.library.controller;

import com.unisinos.library.dto.request.BookRequest;
import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.dto.response.Response;
import com.unisinos.library.model.Book;
import com.unisinos.library.service.BookService;
import com.unisinos.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class BookController extends BaseController {
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

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestHeader("Authorization") String authorization, @RequestBody BookRequest bookRequest) {
        var user = getUserByAuthToken(authorization);

        if (user.isEmpty()) {
            var responseBody = ErrorMessageResponse.builder()
                    .message("User not found")
                    .errorCode("USER_002");

            return ResponseEntity.badRequest().body(responseBody);
        }

        var errors = bookRequest.validate();

        if (!errors.isEmpty()) {
            var response = Response.builder().errorAccumulators(errors).build();
            return ResponseEntity.badRequest().body(response);
        }

        var book = bookRequest.translateToBook();
        var response = bookService.createBook(book, user.get());

        if (response.errorAccumulators != null && !response.errorAccumulators.isEmpty()) {
            return ResponseEntity.badRequest().body(response.errorAccumulators);
        }

        return ResponseEntity.created(URI.create("/books/" + ((Book) response.body).getId())).body(response.body);
    }
}
