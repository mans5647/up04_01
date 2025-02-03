package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.model.Instructors;

public interface InstructorRepository extends JpaRepository<Instructors, Long> {
}
