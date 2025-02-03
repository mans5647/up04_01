package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.model.Groups;

public interface GroupRepository extends JpaRepository<Groups, Long> {
}