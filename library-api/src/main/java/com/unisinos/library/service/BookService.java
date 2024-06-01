package com.unisinos.library.service;

import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.model.Book;
import com.unisinos.library.model.User;
import com.unisinos.library.repository.BookRepository;
import com.unisinos.library.repository.GenreRepository;
import com.unisinos.library.repository.UserRepository;
import com.unisinos.library.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenreRepository genreRepository;

    public List<Book> getBookByParams(String title, String genre) {
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByPartialTitle(title);
        } else if (genre != null && !genre.isEmpty()) {
            return bookRepository.findByGenreName(genre);
        }

        return (List<Book>) bookRepository.findAll();
    }

    public List<Book> getBooksByUser(Long userId) {
        return bookRepository.findAllByOwnerId(userId);
    }

    public Response<?> createBook(Book book, User user) {
        List<ErrorMessageResponse> errors = new ArrayList<>();
        var genre = genreRepository.findById(book.idGenre);

        if (genre.isEmpty()) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("BOOK_001")
                    .field("idGenre")
                    .message("Genre not found")
                    .build();

            errors.add(error);
            return Response.builder().errorAccumulators(errors).build();
        }

        book.setGenre(genre.get());
        book.setOwner(user);

        var newBook =  bookRepository.save(book);
        return Response.builder().body(newBook).build();
    }
}
