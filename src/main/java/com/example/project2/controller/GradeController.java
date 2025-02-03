package com.example.project2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project2.model.Grades;
import com.example.project2.service.AssignmentService;
import com.example.project2.service.ExamService;
import com.example.project2.service.GradeService;
import com.example.project2.service.StudentService;
import com.example.project2.service.SubjectService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private ExamService examService;


    @GetMapping
    public String getAllGrades(Model model) {
        List<Grades> grades = gradeService.getAllGrades();
        model.addAttribute("grades", grades);
        return "grade/list";
    }

    @GetMapping("/create")
    public String createGradeForm(Model model) {
        model.addAttribute("grade", new Grades());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        model.addAttribute("exams", examService.getAllExams());

        return "grade/create";
    }

    @PostMapping
    public String createGrade(@Valid @ModelAttribute("grade") Grades grade, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("grade", grade);
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            model.addAttribute("assignments", assignmentService.getAllAssignments());
            model.addAttribute("exams", examService.getAllExams());

            return "grade/create";
        }
        gradeService.saveGrade(grade);
        return "redirect:/grades";
    }

    @GetMapping("/edit/{id}")
    public String editGradeForm(@PathVariable Long id, Model model) {
        Optional<Grades> grade = gradeService.getGradeById(id);
        if (grade.isPresent()) {
            model.addAttribute("grade", grade.get());
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            model.addAttribute("assignments", assignmentService.getAllAssignments());
            model.addAttribute("exams", examService.getAllExams());

            return "grade/edit";
        }
        return "redirect:/grades";
    }

    @PostMapping("/update/{id}")
    public String updateGrade(@PathVariable Long id, @Valid @ModelAttribute("grade") Grades grade, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("grade", grade);
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            model.addAttribute("assignments", assignmentService.getAllAssignments());
            model.addAttribute("exams", examService.getAllExams());

            return "grade/edit";
        }
        grade.setId(id);
        gradeService.saveGrade(grade);
        return "redirect:/grades";
    }

    @GetMapping("/delete/{id}")
    public String deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return "redirect:/grades";
    }
}
