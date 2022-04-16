package com.app.ELearning.controller;

import com.app.ELearning.model.LoginDTO;
import com.app.ELearning.model.Student;
import com.app.ELearning.model.Tutor;
import com.app.ELearning.repository.StudentRepository;
import com.app.ELearning.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

//indicate the class is a Spring controller.
@Controller
public class AuthenticationController {

    TutorRepository tutorRepository;
    StudentRepository studentRepository;

    //Provides more fine-grained control over where/how @Autowired should be accomplished.
    // used to autowire bean on the setter
    // method just like @Required annotation, constructor, a property or methods
    @Autowired
    public AuthenticationController(TutorRepository tutorRepository, StudentRepository studentRepository) {
        this.tutorRepository = tutorRepository;
        this.studentRepository = studentRepository;
    }
//used to create a resource and @PostMapping annotation for mapping HTTP POST requests onto
// specific handler methods.Composed of anno that acts as shortcut @RequestMapping
    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loginError", true);
            return "login";
        } else {
            Optional<Student> student = studentRepository.findStudentByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
            Optional<Tutor> tutor = tutorRepository.findTutorByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
            if (student.isPresent()) {
                model.addAttribute("studentId", student.get().getStudentId());
                return "redirect:/student/" + student.get().getStudentId();
            } else if (tutor.isPresent()) {
                model.addAttribute("tutorId", tutor.get().getTutorId());
                return "redirect:/tutor/" + tutor.get().getTutorId();
            } else {
                model.addAttribute("loginError", true);
                return "login";
            }
        }
    }
    //maps HTTP GET requests onto specific handler methods.Shortcut for @RequestMapping.
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }
    //maps HTTP GET requests onto specific handler methods.Shortcut for @RequestMapping.
    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }
    //maps HTTP GET requests onto specific handler methods.Shortcut for @RequestMapping.
    @GetMapping("/register/tutor/form")
    public String tutorRegistration(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutor-registration";
    }

    //used to create a resource and @PostMapping annotation for mapping HTTP POST requests onto
// specific handler methods.Composed of anno that acts as shortcut @RequestMapping
    @PostMapping("/register/tutor/form-submission")
    public String tutorRegistrationSubmission(@Valid Tutor tutor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "tutor-registration";
        } else {
            if (tutorRepository.findTutorByUsername(tutor.getUsername()).isPresent()) {
                model.addAttribute("userExisting", true);
                return "tutor-registration";
            } else {
                tutorRepository.save(tutor);
                return "redirect:/";
            }
        }
    }
    //maps HTTP GET requests onto specific handler methods.Shortcut for @RequestMapping.
    @GetMapping("/register/student/form")
    public String studentRegistration(Model model) {
        model.addAttribute("student", new Student());
        return "student-registration";
    }

    //used to create a resource and @PostMapping annotation for mapping HTTP POST requests onto
// specific handler methods.Composed of anno that acts as shortcut @RequestMapping
    @PostMapping("/register/student/form-submission")
    public String studentRegistrationSubmission(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "student-registration";
        } else {
            if (studentRepository.findStudentsByUsername(student.getUsername()).isPresent()) {
                model.addAttribute("userExisting", true);
                return "student-registration";
            } else {
                studentRepository.save(student);
                return "redirect:/";
            }
        }
    }
}
