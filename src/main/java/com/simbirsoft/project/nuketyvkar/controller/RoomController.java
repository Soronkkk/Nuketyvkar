package com.simbirsoft.project.nuketyvkar.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import com.simbirsoft.project.nuketyvkar.dto.RoomDTO;
import com.simbirsoft.project.nuketyvkar.model.Room;
import com.simbirsoft.project.nuketyvkar.services.RoomService;

@Controller
@RequiredArgsConstructor
public class RoomController {


    private final RoomService roomService;

    private final ModelMapper modelMapper;

    @MessageMapping(value = "/rooms/create")
    @SendTo("/topic/public")
    public void createRoom(@Valid RoomDTO roomDTO, @AuthenticationPrincipal Authentication authentication) {
        roomService.save(modelMapper.map(roomDTO, Room.class), authentication.getName());
    }

    // TODO: 13.12.2019  //room remove {Название комнаты} - удалить комнату (владелец и админ)
    // FIXME: 16.12.2019 привести в порядок и проверять роли
    @MessageMapping(value = "/rooms/remove")
    @SendTo("/topic/public")
    public void removeRoom(@Valid RoomDTO roomDTO, @AuthenticationPrincipal Authentication authentication) {
        roomService.delete(roomDTO.getName(), authentication.getName());
    }

    // TODO: 13.12.2019 //room rename {Название комнаты} - переименовать комнату (владелец и админ)
    @PutMapping("/rooms/rename")
    public ModelAndView renameRoom() {
        return null;
    }

    // TODO: 13.12.2019 //room connect {Название комнаты} -l {login пользователя} - добавить пользователя в комнату
    @PostMapping("/rooms/{id}/users/{username}")
    public ModelAndView addUserInRoom() {
        return null;
    }

    // TODO: 13.12.2019 //room disconnect - выйти из текущей комнаты
    // TODO: 13.12.2019 //room disconnect {Название комнаты} - выйти из заданной комнаты
    // TODO: 13.12.2019 //-l {login пользователя} - выгнать пользователя из комнаты (по умолчанию 60 минут) (владелец, админ и модератор)
    // TODO: 13.12.2019 //-m {Количество минут} - время, на которое пользователь не сможет войти (владелец, админ и модератор)
    @DeleteMapping("/rooms/{id}/users/{username}")
    public ModelAndView removeUserFromRoom() {
        return null;
    }

    // TODO: 13.12.2019 //room mute -l {login пользователя} - пользователь не сможет писать в текущей комнате  (владелец, админ и модератор)
    // TODO: 13.12.2019 -m {Количество минут} - время mute режима (по умолчанию 10 минут)
    // TODO: 13.12.2019 room speak -l {login пользователя} - пользователь снова сможет писать в текущей комнате (владелец, админ и модератор)
    @PutMapping("/rooms/{id}/users/{username}")
    public void muteUserInRoom() {

    }


}
