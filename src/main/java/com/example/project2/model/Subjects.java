package com.example.project2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название предмета обязательно.")
    @Size(max = 100, message = "Название предмета должно содержать не более 100 символов.")
    private String name;

    @NotNull(message = "Курс обязателен.")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course;

    @NotNull(message = "Инструктор обязателен.")
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructors instructor;

    @OneToMany(mappedBy = "subject")
    private List<Grades> grades;

    @OneToMany(mappedBy = "subject")
    private List<Exams> exams;
}
