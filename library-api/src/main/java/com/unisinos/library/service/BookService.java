package com.unisinos.library.service;

import com.unisinos.library.model.Book;
import com.unisinos.library.repository.BookRepository;
import com.unisinos.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

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
}
