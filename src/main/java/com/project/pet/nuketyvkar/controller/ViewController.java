package com.project.pet.nuketyvkar.controller;

import com.project.pet.nuketyvkar.services.MessageService;
import com.project.pet.nuketyvkar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("messages", messageService.findAll());
        modelAndView.addObject("users", userService.findAll());
        modelAndView.setViewName("main");
        return modelAndView;
    }
}
