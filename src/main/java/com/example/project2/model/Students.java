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
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя обязательно для заполнения.")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов.")
    private String name;

    @Email(message = "Введите корректный адрес электронной почты.")
    @NotBlank(message = "Электронная почта обязательна для заполнения.")
    @Size(max = 150, message = "Электронная почта должна быть не длиннее 150 символов.")
    private String email;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @NotNull(message = "Группа обязательна для выбора.")
    private Groups group;

    @OneToMany(mappedBy = "student")
    private List<Grades> grades;

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendances;
}
