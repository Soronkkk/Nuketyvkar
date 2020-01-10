package com.simbirsoft.project.nuketyvkar.controller;

import com.simbirsoft.project.nuketyvkar.dto.MessageDTO;
import com.simbirsoft.project.nuketyvkar.dto.RoomDTO;
import com.simbirsoft.project.nuketyvkar.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Message controller.
 */
@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @MessageMapping(value = "/messages")
    @SendTo("/topic/public")
    public MessageDTO sendMessage(@Valid MessageDTO messageDTO, @AuthenticationPrincipal Authentication authentication) {
        return messageService.save(messageDTO, authentication.getName());
    }

}
