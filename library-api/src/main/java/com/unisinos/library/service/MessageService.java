package com.unisinos.library.service;

import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.dto.response.Response;
import com.unisinos.library.model.Message;
import com.unisinos.library.model.User;
import com.unisinos.library.repository.BorrowRepository;
import com.unisinos.library.repository.MessageRepository;
import com.unisinos.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    public Response<?> sendMessage(Long idReceiver, Long idBorrow, String message, User sender) {
        var errors = new ArrayList<ErrorMessageResponse>();
        var receiver = userRepository.findById(idReceiver);
        var borrow = borrowRepository.findById(idBorrow);

        if (receiver.isEmpty()) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("DATA_PROVIDED_INCONSISTENT")
                    .field("idReceiver")
                    .message("Receiver not found")
                    .build();

            errors.add(error);
        }

        if (borrow.isEmpty()) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("DATA_PROVIDED_INCONSISTENT")
                    .field("idBorrow")
                    .message("Borrow not found")
                    .build();

            errors.add(error);
            return Response.builder().errorAccumulators(errors).build();
        }

        if (!Objects.equals(borrow.get().getOwner().getId(), sender.getId())
            && !Objects.equals(borrow.get().getRequester().getId(), sender.getId())) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("BUSINESS_RULE")
                    .message("User cannot send message to this borrow")
                    .field("idBorrow")
                    .build();

            errors.add(error);
        }

        if (!errors.isEmpty()) {
            return Response.builder().errorAccumulators(errors).build();
        }

        var messageToSend = Message
                .builder()
                .messageSent(message)
                .read(Boolean.FALSE)
                .borrow(borrow.get())
                .sender(sender)
                .receiver(receiver.get())
                .build();

        messageToSend = messageRepository.save(messageToSend);

        return Response.builder().body(messageToSend).build();
    }

    public List<Message> getMessages(User user) {
        return messageRepository.findAllByReceiverIdOrSenderId(user.getId(), user.getId());
    }

    public Response<?> receiveMessage (List<Long> idMessages, User user) {
        var messages = (List<Message>) messageRepository.findAllById(idMessages);
        var isReadingWrongMessage = messages.stream().anyMatch(m -> !m.receiver.getId().equals(user.getId()));
        if (isReadingWrongMessage) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("BUSINESS_RULE")
                    .message("User cannot update at least one of these messages")
                    .field("idsMessages")
                    .build();

            return Response.builder().errorAccumulators(List.of(error)).build();
        }

        messages = messages.stream().peek(m -> {
            m.setRead(Boolean.TRUE);
            m.setReadDateTime(ZonedDateTime.now());
        }).toList();

        messageRepository.saveAll(messages);
        return Response.builder().build();
    }

}
