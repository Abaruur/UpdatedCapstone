package com.app.ELearning.controller;

import com.app.ELearning.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//indicate the class is a Spring controller.
@Controller

//to map a HTTP request
//method (GET or POST) to a specific class or method in the controller
@RequestMapping("/class")
public class ClassController {
    private final ClassService classService;

    //Provides more fine-grained control over where/how @Autowired should be accomplished.
    // used to autowire bean on the setter
    // method just like @Required annotation, constructor, a property or methods
    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }
    //maps HTTP GET requests onto specific handler methods.Shortcut for @RequestMapping.
    @GetMapping("/list")
    public String classObjectList(Model model) {
        model.addAttribute("classesList", classService.list());
        return "view-classes";
    }
}
