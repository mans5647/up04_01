package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Long> {
}