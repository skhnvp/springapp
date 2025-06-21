package ru.testproj.springapp.entity;

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
    @Column(name = "email", unique = false, nullable = true, length = 30)
    private String email;
    @Column(name = "birthday", unique = false, nullable = true, length = 30)
    private LocalDate birthday;
    @Column(name = "age", unique = false, nullable = true, length = 3)
    private Integer age;
}
