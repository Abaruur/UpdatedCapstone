package com.app.ELearning.controller;

import com.app.ELearning.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//indicate the class is a Spring controller.
@Controller

//to map a HTTP request
//method (GET or POST) to a specific class or method in the controller
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    //Provides more fine-grained control over where/how @Autowired should be accomplished.
    // used to autowire bean on the setter
    // method just like @Required annotation, constructor, a property or methods
    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }
    //maps HTTP GET requests onto specific handler methods.Shortcut for @RequestMapping.
    @GetMapping("/list/{tutorId}")
    public String lessonList(@PathVariable(name = "tutorId") Integer tutorId, Model model) {
        model.addAttribute("tutorId", tutorId);
        model.addAttribute("lessonsList", lessonService.list());
        return "view-lessons";
    }
}
