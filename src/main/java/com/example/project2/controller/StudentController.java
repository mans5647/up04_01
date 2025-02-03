package com.example.project2.controller;

import com.example.project2.model.Students;
import com.example.project2.service.GroupService;
import com.example.project2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService; // Inject GroupService

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list";
    }

    @GetMapping("/create")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Students());
        model.addAttribute("groups", groupService.getAllGroups()); // Add groups to the model
        return "students/create";
    }

    @PostMapping("/create")
    public String saveStudent(@Valid @ModelAttribute("student") Students student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            model.addAttribute("groups", groupService.getAllGroups()); // Add groups back to the model in case of errors
            return "students/create";
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Students student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        model.addAttribute("groups", groupService.getAllGroups()); // Add groups to the model
        return "students/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("student") Students student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            model.addAttribute("groups", groupService.getAllGroups()); // Add groups back to the model in case of errors
            return "students/edit";
        }
        student.setId(id);
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
