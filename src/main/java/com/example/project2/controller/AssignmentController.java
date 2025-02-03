package com.example.project2.controller;

import com.example.project2.model.Assignments;
import com.example.project2.service.AssignmentService;
import com.example.project2.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public String getAllAssignments(Model model) {
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        return "assignments/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new Assignments());
        model.addAttribute("subjects", subjectService.getAllSubjects()); // Add subjects for creation
        return "assignments/create";
    }

    @PostMapping
    public String createAssignment(@Valid @ModelAttribute("assignment") Assignments assignment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        model.addAttribute("assignment", assignment);

            model.addAttribute("subjects", subjectService.getAllSubjects());  // Add subjects for the create form
            return "assignments/create";
        }
        assignmentService.saveAssignment(assignment);
        return "redirect:/assignments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Assignments assignment = assignmentService.getAssignmentById(id).orElse(null);
        if (assignment == null) {
            return "redirect:/assignments";
        }
        model.addAttribute("assignment", assignment);
        model.addAttribute("subjects", subjectService.getAllSubjects());  // Add subjects for edit form
        return "assignments/edit";
    }

    @PostMapping("/update/{id}")
    public String updateAssignment(@PathVariable Long id, @Valid @ModelAttribute("assignment") Assignments assignment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        model.addAttribute("assignment", assignment);

            model.addAttribute("subjects", subjectService.getAllSubjects());  // Add subjects for the edit form
            return "assignments/edit";
        }
        assignment.setId(id);
        assignmentService.saveAssignment(assignment);
        return "redirect:/assignments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return "redirect:/assignments";
    }
}
