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
public class Exams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название экзамена не может быть пустым")
    @Size(min = 3, max = 255, message = "Название экзамена должно быть от 3 до 255 символов")
    private String title;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @NotNull(message = "Предмет обязателен")
    private Subjects subject;

    @OneToMany(mappedBy = "exam")
    private List<Grades> grades;

}
