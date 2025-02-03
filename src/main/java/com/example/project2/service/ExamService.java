package com.example.project2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project2.model.Exams;
import com.example.project2.repository.ExamRepository;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public List<Exams> getAllExams() {
        return examRepository.findAll();
    }

    public Optional<Exams> getExamById(Long id) {
        return examRepository.findById(id);
    }

    public Exams saveExam(Exams exam) {
        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }
}