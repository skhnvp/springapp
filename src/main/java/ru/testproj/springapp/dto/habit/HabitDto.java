package ru.testproj.springapp.dto.habit;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HabitDto {
    private String name;
    private String description;
    private LocalDate createdAt;
}
