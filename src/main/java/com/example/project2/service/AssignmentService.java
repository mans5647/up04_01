package com.example.project2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project2.model.Assignments;
import com.example.project2.repository.AssignmentRepository;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<Assignments> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignments> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    public Assignments saveAssignment(Assignments assignment) {
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
}