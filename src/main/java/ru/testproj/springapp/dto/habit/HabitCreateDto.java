package ru.testproj.springapp.dto.habit;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HabitCreateDto {
    String name;
    String description;
    LocalDate createdAt;
    Long userId;
}
