package com.project.pet.nuketyvkar.controller;

import com.project.pet.nuketyvkar.model.Message;
import com.project.pet.nuketyvkar.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Main {

    @Autowired
    private MessageService messageService;

    @PostMapping(value = "/messages")
    public ModelAndView sendMessage(@ModelAttribute("message") Message message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Message newMessage = new Message(message.getMessageText());
        messageService.save(newMessage);
        return modelAndView;
    }

}
