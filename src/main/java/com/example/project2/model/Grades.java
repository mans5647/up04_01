package com.example.project2.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Оценка не может быть пустой")
    @Min(value = 0, message = "Оценка не может быть меньше 0")
    @Max(value = 100, message = "Оценка не может быть больше 100")
    private Integer grade;

    @NotNull(message = "Студент должен быть выбран")
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Students student;

    @NotNull(message = "Предмет должен быть выбран")
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subjects subject;

    @NotNull(message = "Задание должно быть выбрано")
    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignments assignment;

    @NotNull(message = "Экзамен должен быть выбран")
    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exams exam;

    // Getters and Setters
}