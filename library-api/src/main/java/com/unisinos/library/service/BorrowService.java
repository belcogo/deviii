package com.unisinos.library.service;

import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.dto.response.Response;
import com.unisinos.library.model.Borrow;
import com.unisinos.library.model.BorrowStatus;
import com.unisinos.library.model.User;
import com.unisinos.library.repository.BookRepository;
import com.unisinos.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookRepository bookRepository;

    private final List<BorrowStatus> notPossibleToBorrowStatus = List.of(BorrowStatus.ACCEPTED, BorrowStatus.WAITING_TO_RETURN);

    public Response<?> createBorrow(Long idBook, User userRequester) {
        var errors = new ArrayList<ErrorMessageResponse>();
        var book = bookRepository.findById(idBook);

        if (book.isEmpty()) {
            var error = ErrorMessageResponse.builder()
                    .errorCode("DATA_PROVIDED_INCONSISTENT")
                    .message("Invalid book provided")
                    .field("idBook")
                    .build();

            errors.add(error);
            return Response.builder().errorAccumulators(errors).build();
        }

        if (Objects.equals(book.get().getOwner().getId(), userRequester.getId())) {
            var error = ErrorMessageResponse.builder()
                    .errorCode("BUSINESS_RULE")
                    .message("User cannot borrow their own book")
                    .field("idBook")
                    .build();

            errors.add(error);
            return Response.builder().errorAccumulators(errors).build();
        }

        boolean cannotBorrowBook = borrowRepository
                .existsByBookRequestedIdAndBorrowStatusIn(book.get().getId(), notPossibleToBorrowStatus);

        if (cannotBorrowBook) {
            var error = ErrorMessageResponse.builder()
                    .errorCode("BUSINESS_RULE")
                    .message("Book is not available")
                    .field("idBook")
                    .build();

            errors.add(error);
            return Response.builder().errorAccumulators(errors).build();
        }

        var borrow = Borrow
                .builder()
                .borrowStatus(BorrowStatus.PENDING)
                .bookRequested(book.get())
                .owner(book.get().getOwner())
                .requester(userRequester)
                .lastUpdatedTime(ZonedDateTime.now())
                .build();

        var borrowCreated = borrowRepository.save(borrow);

        return Response.builder().body(borrowCreated).build();
    }

    public Response<?> changeBorrowedBook(Long idBorrow, User userChanging, String borrowStatus) {
        var borrow = borrowRepository.findById(idBorrow);
        var borrowStatusEnum = BorrowStatus.valueOf(borrowStatus);

        if (borrow.isEmpty()) {
            var error = ErrorMessageResponse.builder()
                    .errorCode("DATA_PROVIDED_INCONSISTENT")
                    .message("Invalid borrow provided")
                    .field("id")
                    .build();

            return Response.builder().errorAccumulators(List.of(error)).build();
        }

        if (!Objects.equals(borrow.get().getRequester().getId(), userChanging.getId()) &&
                Objects.equals(borrow.get().getOwner().getId(), userChanging.getId()) ) {
            var error = ErrorMessageResponse.builder()
                    .errorCode("BUSINESS_RULE")
                    .message("User cannot change this borrow")
                    .field("id")
                    .build();

            return Response.builder().errorAccumulators(List.of(error)).build();
        }

        var borrowChanged = switch (borrowStatusEnum) {
            case ACCEPTED -> approveBorrow(borrow.get());
            case REJECTED -> rejectBorrow(borrow.get());
            case WAITING_TO_RETURN -> changeBorrowToWaitingReturn(borrow.get());
            case RETURNED -> returnBook(borrow.get());
            default -> borrow.get();
        };

        return Response.builder().body(borrowChanged).build();
    }

    public Borrow approveBorrow(Borrow borrow) {
        borrow.borrowStatus = BorrowStatus.ACCEPTED;
        borrow.lastUpdatedTime = ZonedDateTime.now();

        borrowRepository.updatePendingToRejected(borrow.getBookRequested().getId());

        return borrowRepository.save(borrow);
    }

    public Borrow changeBorrowToWaitingReturn(Borrow borrow) {
        borrow.borrowStatus = BorrowStatus.WAITING_TO_RETURN;
        borrow.lastUpdatedTime = ZonedDateTime.now();

        return borrowRepository.save(borrow);
    }

    public Borrow returnBook(Borrow borrow) {
        borrow.borrowStatus = BorrowStatus.RETURNED;
        borrow.lastUpdatedTime = ZonedDateTime.now();

        return borrowRepository.save(borrow);
    }

    public Borrow rejectBorrow(Borrow borrow) {
        borrow.borrowStatus = BorrowStatus.REJECTED;
        borrow.lastUpdatedTime = ZonedDateTime.now();

        return borrowRepository.save(borrow);
    }
}
