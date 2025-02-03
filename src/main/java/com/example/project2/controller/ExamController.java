package com.example.project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.project2.model.Exams;
import com.example.project2.service.ExamService;
import com.example.project2.service.SubjectService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private SubjectService subjectService; // To fetch subjects for dropdown options

    @GetMapping
    public String getAllExams(Model model) {
        List<Exams> exams = examService.getAllExams();
        model.addAttribute("exams", exams);
        return "exam/list";
    }

    @GetMapping("/create")
    public String createExamForm(Model model) {
        model.addAttribute("exam", new Exams());
        model.addAttribute("subjects", subjectService.getAllSubjects()); // Fetch all subjects for dropdown
        return "exam/create";
    }

    @PostMapping
    public String createExam(@Valid @ModelAttribute("exam") Exams exam, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exam", exam);
            model.addAttribute("subjects", subjectService.getAllSubjects());
            return "exam/create";
        }
        examService.saveExam(exam);
        return "redirect:/exams";
    }

    @GetMapping("/edit/{id}")
    public String editExamForm(@PathVariable Long id, Model model) {
        Optional<Exams> exam = examService.getExamById(id);
        if (exam.isPresent()) {
            model.addAttribute("exam", exam.get());
            model.addAttribute("subjects", subjectService.getAllSubjects()); // Fetch all subjects for dropdown
        }
        return "exam/edit";
    }

    @PostMapping("/update/{id}")
    public String updateExam(@PathVariable Long id, @Valid @ModelAttribute("exam") Exams exam, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exam", exam);
            model.addAttribute("subjects", subjectService.getAllSubjects());
            return "exam/edit";
        }
        exam.setId(id);
        examService.saveExam(exam);
        return "redirect:/exams";
    }

    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return "redirect:/exams";
    }
}
