package ru.testproj.springapp.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateDto {
    private String name;
    private String email;
    private LocalDate birthday;
}
