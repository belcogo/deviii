package com.unisinos.library.controller;

import com.unisinos.library.dto.request.BorrowRequest;
import com.unisinos.library.dto.request.BorrowUpdateRequest;
import com.unisinos.library.dto.response.Response;
import com.unisinos.library.model.Borrow;
import com.unisinos.library.repository.BorrowRepository;
import com.unisinos.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

@RestController
public class BorrowController extends BaseController {
    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    BorrowService borrowService;

    @GetMapping("users/{idUser}/borrows")
    public ResponseEntity<?> getUserBorrows(@PathVariable("idUser") Long idUser, @RequestHeader("Authorization") String authorization) {
        var userAuthenticated = getUserByAuthToken(authorization);

        if (userAuthenticated.isEmpty() || !Objects.equals(userAuthenticated.get().getId(), idUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("aa");
        }

        var borrows = borrowRepository.findByOwnerIdOrRequesterId(userAuthenticated.get().id, userAuthenticated.get().id);

        return ResponseEntity.ok(borrows);
    }

    @PostMapping("/borrows")
    public ResponseEntity<?> createBorrow(@RequestBody BorrowRequest request, @RequestHeader("Authorization") String authorization) {
        var userAuthenticated = getUserByAuthToken(authorization);
        var errors = request.validate();

        if (!errors.isEmpty()) {
            var response = Response.builder().errorAccumulators(errors).build();
            return ResponseEntity.badRequest().body(response);
        }

        var response = borrowService.createBorrow(request.idBook, userAuthenticated.get());

        if (response.errorAccumulators != null && !response.errorAccumulators.isEmpty()) {
            return ResponseEntity.badRequest().body(response);
        }

        var createdBorrowId = ((Borrow) response.body).id;
        return ResponseEntity.created(URI.create("/borrows/" + createdBorrowId)).body(response.body);
    }

    @PutMapping("/borrows")
        public ResponseEntity<?> updateBorrow(@RequestBody BorrowUpdateRequest request, @RequestHeader("Authorization") String authorization) {
        var userAuthenticated = getUserByAuthToken(authorization);
        var errors = request.validate();

        if (!errors.isEmpty()) {
            var response = Response.builder().errorAccumulators(errors).build();
            return ResponseEntity.badRequest().body(response);
        }

        var response = borrowService.changeBorrowedBook(request.id, userAuthenticated.get(), request.borrowStatus);

        if (response.errorAccumulators != null && !response.errorAccumulators.isEmpty()) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response.body);
    }

}
