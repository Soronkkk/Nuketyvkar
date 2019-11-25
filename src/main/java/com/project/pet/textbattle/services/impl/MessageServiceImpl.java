package com.project.pet.textbattle.services.impl;

import com.project.pet.textbattle.model.Message;
import com.project.pet.textbattle.repositories.MessageRepository;
import com.project.pet.textbattle.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Transactional
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
