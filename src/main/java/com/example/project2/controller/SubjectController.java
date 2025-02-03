package com.example.project2.controller;

import com.example.project2.model.Subjects;
import com.example.project2.service.CourseService;
import com.example.project2.service.InstructorService;
import com.example.project2.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String getAllSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subject/list"; // View for displaying all subjects
    }

    @GetMapping("/create")
    public String createSubjectForm(Model model) {
        model.addAttribute("subject", new Subjects());
        model.addAttribute("instructors", instructorService.getAllInstructors());
        model.addAttribute("courses", courseService.getAllCourses());
        return "subject/create";
    }

    @PostMapping
    public String saveSubject(
            @Valid @ModelAttribute("subject") Subjects subject,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
        model.addAttribute("courses", courseService.getAllCourses());

            model.addAttribute("instructors", instructorService.getAllInstructors());
            model.addAttribute("courses", courseService.getAllCourses());
            return "subject/create";
        }
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/edit/{id}")
    public String editSubjectForm(@PathVariable Long id, Model model) {
        Optional<Subjects> subject = subjectService.getSubjectById(id);
        if (subject.isPresent()) {
            model.addAttribute("subject", subject.get());
            model.addAttribute("instructors", instructorService.getAllInstructors());
            model.addAttribute("courses", courseService.getAllCourses());
            return "subject/edit";
        }
        return "redirect:/subjects";
    }

    @PostMapping("/update/{id}")
    public String updateSubject(
            @PathVariable Long id,
            @Valid @ModelAttribute("subject") Subjects subject,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
        model.addAttribute("courses", courseService.getAllCourses());

            model.addAttribute("instructors", instructorService.getAllInstructors());
            model.addAttribute("courses", courseService.getAllCourses());
            return "subject/edit";
        }
        subject.setId(id);
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return "redirect:/subjects";
    }
}
