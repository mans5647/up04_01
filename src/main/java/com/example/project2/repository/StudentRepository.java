package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.model.Students;

public interface StudentRepository extends JpaRepository<Students, Long> {
}