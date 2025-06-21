package ru.testproj.springapp.service;

import org.springframework.stereotype.Service;
import ru.testproj.springapp.dto.habitlog.HabitLogChangeStatusDto;
import ru.testproj.springapp.dto.habitlog.HabitLogDto;
import ru.testproj.springapp.entity.HabitLog;
import ru.testproj.springapp.mapper.HabitLogMapper;
import ru.testproj.springapp.repository.HabitLogRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitLogService {
    private final HabitLogRepository habitLogRepository;
    private final HabitLogMapper habitLogMapper;

    public HabitLogService(HabitLogRepository habitLogRepository, HabitLogMapper habitLogMapper) {
        this.habitLogRepository = habitLogRepository;
        this.habitLogMapper = habitLogMapper;
    }

    public HabitLogDto changeStatus(HabitLogChangeStatusDto dto) {
        if (habitLogRepository.findByHabitId(dto.getHabit_id()) != null) {
            throw new IllegalArgumentException("This Habit is not exist");
        }

        HabitLog habitLog = habitLogRepository.findByHabitId(dto.getHabit_id());
        habitLog.setCompleted(dto.isCompleted());
        habitLog.setUpdateDate(LocalDateTime.now());

        habitLogRepository.save(habitLog);
        return habitLogMapper.toDto(habitLog);
    }

    public List<HabitLogDto> getHabitsFromPeriod(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Dates are expected.");
        }

        LocalDateTime fromStart = from.atStartOfDay();
        LocalDateTime toEnd = to.atTime(23, 59, 59);

        return habitLogRepository.findAllByUpdateDateBetween(fromStart, toEnd)
                .stream()
                .map(habitLogMapper::toDto)
                .toList();
    }
}
