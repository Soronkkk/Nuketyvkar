package com.simbirsoft.project.nuketyvkar.controller;

import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.simbirsoft.project.nuketyvkar.constants.RoleConstant;
import com.simbirsoft.project.nuketyvkar.dto.RoomDTO;
import com.simbirsoft.project.nuketyvkar.services.RoomService;
import com.simbirsoft.project.nuketyvkar.services.UserService;

/**
 * @author Koloyartsev V.A.
 * <p>
 * View controller.
 */
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final RoomService roomService;

    private final UserService userService;

    // TODO: 20.12.2019 Возвращать только комнаты и отображать сообщения и пользователей по текущей комнате в которой находится пользователь
    @GetMapping("/")
    public ModelAndView getMessages(ZoneId zoneId, @AuthenticationPrincipal Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        List<RoomDTO> rooms = roomService.findAllByUsernameAndRoleName(authentication.getName(), RoleConstant.ROLE_USER);
        modelAndView.addObject("rooms", rooms);
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("zonedid", zoneId);
        modelAndView.setViewName("index");

        return modelAndView;
    }

}
