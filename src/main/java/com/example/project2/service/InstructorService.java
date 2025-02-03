package com.example.project2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project2.model.Instructors;
import com.example.project2.repository.InstructorRepository;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructors> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructors> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructors saveInstructor(Instructors instructor) {
        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}
