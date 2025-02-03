package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.model.Assignments;

public interface AssignmentRepository extends JpaRepository<Assignments, Long> {
}
