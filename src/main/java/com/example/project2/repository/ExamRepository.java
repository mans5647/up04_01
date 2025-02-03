package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.model.Exams;

public interface ExamRepository extends JpaRepository<Exams, Long> {
}