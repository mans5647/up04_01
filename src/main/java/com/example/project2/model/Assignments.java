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
public class Assignments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название задания не может быть пустым")
    @Size(min = 3, max = 100, message = "Название задания должно быть от 3 до 100 символов")
    private String title;

    @NotNull(message = "Описание задания не может быть пустым")
    @Size(min = 10, max = 500, message = "Описание задания должно быть от 10 до 500 символов")
    private String description;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subjects subject;

    @OneToMany(mappedBy = "assignment")
    private List<Grades> grades;
}
