package com.unisinos.library.dto.request;

import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.model.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookRequest {
    public String title;
    public Long idGenre;
    public String author;
    public LocalDate publishedDate;

    public Book translateToBook() {
        return Book.builder()
                .title(title)
                .author(author)
                .idGenre(idGenre)
                .publishedDate(publishedDate)
                .build();
    }

    public List<ErrorMessageResponse> validate() {
        var erros = new ArrayList<ErrorMessageResponse>();

        if (title == null || title.isEmpty()) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("BOOK_001")
                    .field("title")
                    .message("Title must be provided")
                    .build();

            erros.add(error);
        }

        if (idGenre == null) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("BOOK_001")
                    .field("idGenre")
                    .message("IdGenre must be provided")
                    .build();

            erros.add(error);
        }

        if (author == null || author.isEmpty()) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("BOOK_001")
                    .field("author")
                    .message("Author must be provided")
                    .build();

            erros.add(error);
        }

        if (publishedDate == null) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("BOOK_001")
                    .field("publishedDate")
                    .message("PublishedDate must be provided")
                    .build();

            erros.add(error);
        }


        return erros;
    }
}
