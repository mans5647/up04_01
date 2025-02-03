package com.example.project2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название курса обязательно")
    @Size(max = 100, message = "Название курса не должно превышать 100 символов")
    private String name;

    @Size(max = 500, message = "Описание курса не должно превышать 500 символов")
    private String description;

    @NotNull(message = "Дата начала обязательна")
    @FutureOrPresent(message = "Дата начала должна быть сегодняшним или будущим днем")
    private LocalDate startDate;

    @NotNull(message = "Дата окончания обязательна")
    @Future(message = "Дата окончания должна быть в будущем")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @NotNull(message = "Инструктор обязателен")
    private Instructors instructor;

    @Min(value = 1, message = "Максимум студентов должен быть не меньше 1")
    @Max(value = 1000, message = "Максимум студентов не должен превышать 1000")
    private int maxStudents;

    @OneToMany(mappedBy = "course")
    private List<Subjects> subjects;

    // Getters and Setters
}
