package com.simbirsoft.project.nuketyvkar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // TODO: 13.12.2019  //user rename {login пользователя}||{Новый login пользователя} - переименовать пользователя (владелец и админ)
    @PutMapping("/users/{username}/rename")
    public ModelAndView renameUser() {
        return null;
    }

    // TODO: 13.12.2019  //user ban {login пользователя} - выгнать пользователя из всех комнат навсегда (админ и модератор)
    // TODO: 13.12.2019  -m {Количество минут} - время, на которое пользователь не сможет войти
    @PutMapping("/users/{username}/ban")
    public void banUser() {
    }

    // TODO: 13.12.2019 //user pardon {login пользователя} - разблокировать пользователя во всех комнатах (админ и модератор)
    @PutMapping("/users/{username}/pardon")
    public void pardonUser() {
    }

    // TODO: 13.12.2019 //user moderator {login пользователя} - действия над модераторами (админ)
    // TODO: 13.12.2019  -n - назначить пользователя модератором
    @PostMapping("/users/{username}/moder")
    public void addModerUser() {
    }

    // TODO: 13.12.2019  -d - разжаловать пользователя
    @DeleteMapping("/users/{username}/unmoder")
    public void removeModerUser() {
    }

}
