package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.model.Subjects;

public interface SubjectRepository extends JpaRepository<Subjects, Long> {
}