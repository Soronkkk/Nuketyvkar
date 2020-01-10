package com.simbirsoft.project.nuketyvkar.services;

import com.simbirsoft.project.nuketyvkar.dto.MessageDTO;

import java.util.List;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Message service.
 */
public interface MessageService {

    /**
     * Saved message in data base.
     *
     * @param messageDTO data access object message.
     * @return messageDTO.
     */
    MessageDTO save(MessageDTO messageDTO, String username);

    /**
     * Return all messages list from data base.
     *
     * @return messages dto list.
     */
    List<MessageDTO> findAll();

}
