package ru.testproj.springapp.mapper;

import org.mapstruct.Mapper;
import ru.testproj.springapp.dto.habitlog.HabitLogChangeStatusDto;
import ru.testproj.springapp.dto.habitlog.HabitLogDto;
import ru.testproj.springapp.entity.HabitLog;

@Mapper(componentModel = "spring")
public interface HabitLogMapper {
    HabitLogDto toDto(HabitLog habitlog);
    HabitLog toEntity(HabitLogChangeStatusDto dto);
}
