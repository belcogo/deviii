package com.unisinos.library.repository;

import com.unisinos.library.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAllByReceiverIdOrSenderId(Long receiverId, Long senderId);
}
