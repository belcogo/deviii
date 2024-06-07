package com.unisinos.library.repository;

import com.unisinos.library.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByOwnerId(Long ownerId);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:partialTitle%")
    List<Book> findByPartialTitle(@Param("partialTitle") String partialTitle);

    List<Book> findByGenreName(String genreName);

    @Query("SELECT b FROM Book b WHERE b.owner.id = :userId OR b.id IN (SELECT br.bookRequested.id FROM Borrow br WHERE br.requester.id = :userId AND br.borrowStatus = com.unisinos.library.model.BorrowStatus.ACCEPTED)")
    List<Book> getAllAssociatedBooks(Long userId);
}
