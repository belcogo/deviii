package com.unisinos.library.controller;

import com.unisinos.library.dto.request.MessageRequest;
import com.unisinos.library.dto.request.MessageUpdateRequest;
import com.unisinos.library.dto.response.Response;
import com.unisinos.library.model.Message;
import com.unisinos.library.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class MessageController extends BaseController {
    @Autowired
    MessageService messageService;

    @PostMapping("/borrows/{idBorrow}/messages")
    public ResponseEntity<?> sendMessage(@PathVariable("idBorrow") Long idBorrow,
                                         @RequestBody MessageRequest request,
                                         @RequestHeader("Authorization") String authorization) {
        var userAuthenticated = getUserByAuthToken(authorization);

        var errors = request.validate();

        if (!errors.isEmpty()) {
            var response = Response.builder().errorAccumulators(errors).build();
            return ResponseEntity.badRequest().body(response);
        }

        var response = messageService.sendMessage(request.idReceiver, idBorrow, request.message, userAuthenticated.get());

        if (response.errorAccumulators != null && !response.errorAccumulators.isEmpty()) {
            return ResponseEntity.badRequest().body(response);
        }

        var createdMessageId = ((Message) response.body).id;
        return ResponseEntity.created(URI.create("/messages/" + createdMessageId)).body(response.body);
    }

    @PutMapping("/messages")
    public ResponseEntity<?> updateMessage(@RequestBody MessageUpdateRequest request,
                                         @RequestHeader("Authorization") String authorization) {
        var userAuthenticated = getUserByAuthToken(authorization);

        var errors = request.validate();

        if (!errors.isEmpty()) {
            var response = Response.builder().errorAccumulators(errors).build();
            return ResponseEntity.badRequest().body(response);
        }

        var response = messageService.receiveMessage(request.idMessages, userAuthenticated.get());

        if (response.errorAccumulators != null && !response.errorAccumulators.isEmpty()) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getMessages(@RequestHeader("Authorization") String authorization) {
        var userAuthenticated = getUserByAuthToken(authorization);
        var messages = messageService.getMessages(userAuthenticated.get());

        return ResponseEntity.ok(messages);
    }
}
