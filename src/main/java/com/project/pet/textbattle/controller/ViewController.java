package com.project.pet.textbattle.controller;

import com.project.pet.textbattle.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("messages", messageService.findAll());
        modelAndView.setViewName("main");
        return modelAndView;
    }
}
