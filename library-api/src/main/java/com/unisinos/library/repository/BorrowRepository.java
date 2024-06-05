package com.unisinos.library.repository;

import com.unisinos.library.model.Borrow;
import com.unisinos.library.model.BorrowStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowRepository extends CrudRepository<Borrow, Long> {
    List<Borrow> findByOwnerIdOrRequesterId(Long ownerId, Long requesterId);

    boolean existsByBookRequestedIdAndBorrowStatusIn(Long idBookRequested, List<BorrowStatus> borrowStatuses);

    @Transactional
    @Modifying
    @Query("UPDATE Borrow b SET b.borrowStatus = com.unisinos.library.model.BorrowStatus.REJECTED WHERE b.bookRequested.id = :idBookRequested AND b.borrowStatus = com.unisinos.library.model.BorrowStatus.PENDING")
    void updatePendingToRejected(@Param("idBookRequested") Long idBookRequested);
}
