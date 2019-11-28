package com.project.pet.nuketyvkar.services.impl;

import com.project.pet.nuketyvkar.model.Message;
import com.project.pet.nuketyvkar.repositories.MessageRepository;
import com.project.pet.nuketyvkar.repositories.UserRepository;
import com.project.pet.nuketyvkar.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public Message save(Message message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        message.setAuthor(userRepository.findUserByUsername(authentication.getName()).get());
        message.setCreatedDateTime(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @Transactional
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
