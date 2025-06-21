package ru.testproj.springapp.dto.habitlog;

import lombok.Data;

@Data
public class HabitLogChangeStatusDto {
    private boolean completed;
    private Long habit_id;
}
