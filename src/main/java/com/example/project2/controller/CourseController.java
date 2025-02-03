package com.example.project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project2.model.Courses;
import com.example.project2.service.CourseService;
import com.example.project2.service.InstructorService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    // List all courses
    @GetMapping
    public String listCourses(Model model) {
        List<Courses> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses/list";
    }

    // Show form to create a new course
    @GetMapping("/create")
    public String createCourseForm(Model model) {
        model.addAttribute("course", new Courses());
        model.addAttribute("instructors", instructorService.getAllInstructors()); // Populate instructors dropdown
        return "courses/create"; // View name for course creation form
    }

    // Save a new course
    @PostMapping
    public String saveCourse(@Valid @ModelAttribute("course") Courses course,
                             BindingResult result,
                             RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {

            model.addAttribute("course", course);

            model.addAttribute("instructors", instructorService.getAllInstructors()); // Populate instructors dropdown

            return "courses/create"; // Show form again with errors
        }
        courseService.saveCourse(course);
        redirectAttributes.addFlashAttribute("message", "Course created successfully!");
        return "redirect:/courses";
    }

    // Show form to edit an existing course
    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable Long id, Model model) {
        Courses course = courseService.getCourseById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course ID:" + id));
        model.addAttribute("course", course);
        model.addAttribute("instructors", instructorService.getAllInstructors()); // Ensure instructors are available for editing
        return "courses/edit";
    }

    // Update an existing course
    @PostMapping("/edit/{id}")
    public String updateCourse(@PathVariable Long id,
                               @Valid @ModelAttribute("course") Courses course,
                               BindingResult result,
                               RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("course", course);
            model.addAttribute("instructors", instructorService.getAllInstructors()); // Ensure instructors are available for editing
        
            return "courses/edit"; // Show form again with errors
        }
        course.setId(id); // Ensure the correct course ID is set
        courseService.saveCourse(course);
        redirectAttributes.addFlashAttribute("message", "Course updated successfully!");
        return "redirect:/courses";
    }

    // Delete a course
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        courseService.deleteCourse(id);
        redirectAttributes.addFlashAttribute("message", "Course deleted successfully!");
        return "redirect:/courses";
    }
}
