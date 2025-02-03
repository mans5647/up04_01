package com.example.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}