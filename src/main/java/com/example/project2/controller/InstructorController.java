package com.example.project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.project2.model.Instructors;
import com.example.project2.service.InstructorService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public String getAllInstructors(Model model) {
        List<Instructors> instructors = instructorService.getAllInstructors();
        model.addAttribute("instructors", instructors);
        return "instructor/list"; // Страница со списком инструкторов
    }

    @GetMapping("/create")
    public String createInstructorForm(Model model) {
        model.addAttribute("instructor", new Instructors());
        return "instructor/create"; // Страница создания инструктора
    }

    @PostMapping
    public String saveInstructor(@Valid @ModelAttribute("instructor") Instructors instructor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("instructor", instructor); // Возвращаем введенные данные в форму
            return "instructor/create";
        }
        instructorService.saveInstructor(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/edit/{id}")
    public String editInstructorForm(@PathVariable Long id, Model model) {
        Optional<Instructors> instructor = instructorService.getInstructorById(id);
        if (instructor.isPresent()) {
            model.addAttribute("instructor", instructor.get());
            return "instructor/edit"; // Страница редактирования инструктора
        }
        return "redirect:/instructors";
    }

    @PostMapping("/update/{id}")
    public String updateInstructor(@PathVariable Long id, @Valid @ModelAttribute("instructor") Instructors instructor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            instructor.setId(id); // Сохраняем ID после ошибок валидации
            model.addAttribute("instructor", instructor);
            return "instructor/edit";
        }
        instructor.setId(id);
        instructorService.saveInstructor(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return "redirect:/instructors";
    }
}
