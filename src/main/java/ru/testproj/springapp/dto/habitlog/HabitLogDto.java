package ru.testproj.springapp.dto.habitlog;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HabitLogDto {
    private LocalDate date;
    private boolean completed;
    private Long habit_id;
}
