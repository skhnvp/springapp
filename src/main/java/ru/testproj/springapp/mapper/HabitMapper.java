package ru.testproj.springapp.mapper;

import org.mapstruct.Mapper;
import ru.testproj.springapp.dto.habit.HabitCreateDto;
import ru.testproj.springapp.dto.habit.HabitDto;
import ru.testproj.springapp.entity.Habit;

@Mapper(componentModel = "spring")
public interface HabitMapper {
    HabitDto toDto(Habit habit);
    Habit toEntity(HabitCreateDto dto);
}
