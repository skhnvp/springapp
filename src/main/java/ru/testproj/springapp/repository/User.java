package ru.testproj.springapp.repository;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY - генерацией занимается бд
    private Long id;
    @Column(name = "name", unique = false, nullable = true, length = 30)
    private String name;
    private String email;
    private LocalDate birthday;
    private Integer age;

    public User(Long id, String name, String email, LocalDate birthday, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.age = age;
    }

    public User() {

    }
}
