package ru.testproj.springapp.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY - генерацией занимается бд
    private Long id;
    @Column(name = "name", unique = false, nullable = true, length = 30)
    private String name;
    private String email;
    private LocalDate birthday;
    private Integer age;
}
