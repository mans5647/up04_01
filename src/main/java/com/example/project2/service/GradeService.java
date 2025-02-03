package com.example.project2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project2.model.Grades;
import com.example.project2.repository.GradeRepository;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grades> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Optional<Grades> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }

    public Grades saveGrade(Grades grade) {
        return gradeRepository.save(grade);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}