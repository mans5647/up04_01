package com.example.project2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project2.model.Attendance;
import com.example.project2.service.AttendanceService;
import com.example.project2.service.StudentService;
import com.example.project2.service.SubjectService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentsService;  // Assuming you have a StudentsService
    @Autowired
    private SubjectService subjectsService;  // Assuming you have a SubjectsService

    @GetMapping
    public String getAllAttendance(Model model) {
        List<Attendance> attendances = attendanceService.getAllAttendances();
        model.addAttribute("attendances", attendances);
        return "attendance/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        model.addAttribute("students", studentsService.getAllStudents());
        model.addAttribute("subjects", subjectsService.getAllSubjects());
        return "attendance/create";
    }

    @PostMapping
    public String createAttendance(@ModelAttribute("attendance") Attendance attendance) {
        attendanceService.saveAttendance(attendance);
        return "redirect:/attendance";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Attendance attendance = attendanceService.getAttendanceById(id).orElse(null);
        model.addAttribute("attendance", attendance);
        model.addAttribute("students", studentsService.getAllStudents());
        model.addAttribute("subjects", subjectsService.getAllSubjects());
        return "attendance/edit";
    }

    @PostMapping("/update/{id}")
    public String updateAttendance(@PathVariable Long id, @ModelAttribute("attendance") Attendance attendance) {
        attendance.setId(id);
        attendanceService.saveAttendance(attendance);
        return "redirect:/attendance";
    }

    @GetMapping("/delete/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return "redirect:/attendance";
    }
}
