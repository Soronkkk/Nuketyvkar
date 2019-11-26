package com.project.pet.nuketyvkar.services;

import com.project.pet.nuketyvkar.model.Message;

import java.util.List;

public interface MessageService {

    /**
     * Сохраняет сообщение в бд.
     *
     * @param message сообщение.
     * @return сообщение.
     */
    Message save(Message message);

    /**
     * Возвращает список всех сообщений из бд.
     *
     * @return список сообщений.
     */
    List<Message> findAll();

}
