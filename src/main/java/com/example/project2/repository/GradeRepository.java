package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.model.Grades;

public interface GradeRepository extends JpaRepository<Grades, Long> {
}