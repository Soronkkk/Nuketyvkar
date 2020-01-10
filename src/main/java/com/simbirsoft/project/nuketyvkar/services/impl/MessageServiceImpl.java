package com.simbirsoft.project.nuketyvkar.services.impl;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simbirsoft.project.nuketyvkar.dto.MessageDTO;
import com.simbirsoft.project.nuketyvkar.filter.specification.UserSpecification;
import com.simbirsoft.project.nuketyvkar.model.Message;
import com.simbirsoft.project.nuketyvkar.model.Room;
import com.simbirsoft.project.nuketyvkar.model.User;
import com.simbirsoft.project.nuketyvkar.repositories.MessageRepository;
import com.simbirsoft.project.nuketyvkar.services.MessageService;
import com.simbirsoft.project.nuketyvkar.services.RoomService;
import com.simbirsoft.project.nuketyvkar.services.UserService;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Message service implementation.
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final UserService userService;

    private final RoomService roomService;

    private final ModelMapper modelMapper;

    // TODO: 27.12.2019 Доделать отправку сообщений после реализации чатовых команд
    @Transactional
    public MessageDTO save(MessageDTO messageDTO, String username) {
        Message message = modelMapper.map(messageDTO, Message.class);
        message.setCreatedDateTime(ZonedDateTime.now());
        message.setAuthor(modelMapper.map(userService.findUserByUsername(UserSpecification.findByUsername(username)), User.class));

        message.setRoom(modelMapper.map(roomService.findById(messageDTO.getRoom().getId()), Room.class));

        messageRepository.save(message);
        return convertToDTO(message);
    }

    @Transactional
    public List<MessageDTO> findAll() {
        return messageRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private MessageDTO convertToDTO(Message message) {
        return modelMapper.map(message, MessageDTO.class);
    }


}
