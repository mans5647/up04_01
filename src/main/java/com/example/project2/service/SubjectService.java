package com.example.project2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project2.model.Subjects;
import com.example.project2.repository.SubjectRepository;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subjects> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subjects> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public Subjects saveSubject(Subjects subject) {
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
