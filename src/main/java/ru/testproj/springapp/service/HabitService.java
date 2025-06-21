package ru.testproj.springapp.service;

import org.springframework.stereotype.Service;
import ru.testproj.springapp.dto.habit.HabitCreateDto;
import ru.testproj.springapp.dto.habit.HabitDto;
import ru.testproj.springapp.entity.Habit;
import ru.testproj.springapp.entity.HabitLog;
import ru.testproj.springapp.entity.User;
import ru.testproj.springapp.mapper.HabitLogMapper;
import ru.testproj.springapp.mapper.HabitMapper;
import ru.testproj.springapp.repository.HabitLogRepository;
import ru.testproj.springapp.repository.HabitRepository;
import ru.testproj.springapp.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitService {
    private final HabitRepository habitRepository;
    private final HabitMapper habitMapper;
    private final UserRepository userRepository;
    private final HabitLogRepository habitLogRepository;

    public HabitService(HabitRepository habitRepository, HabitMapper habitMapper, UserRepository userRepository, HabitLogRepository habitLogRepository) {
        this.habitRepository = habitRepository;
        this.habitMapper = habitMapper;
        this.userRepository = userRepository;
        this.habitLogRepository = habitLogRepository;
    }

    public List<HabitDto> findAllByUserId(Long userId) {
        List<Habit> habits = habitRepository.findAllByUserId(userId);
        return habits.stream().map(habitMapper::toDto).toList();
    }

    public HabitDto create(HabitCreateDto dto) {
        if (habitRepository.findByName(dto.getName()) != null) {
            throw new IllegalArgumentException("Habit with this name already exists");
        }

        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Habit habit = habitMapper.toEntity(dto);
        habit.setCreatedAt(LocalDate.now());
        habit.setUser(user);
        habit = habitRepository.save(habit);

        HabitLog habitLog = new HabitLog();
        habitLog.setHabit(habit);
        habitLog.setUpdateDate(LocalDateTime.now());
        habitLog.setCompleted(false);
        habitLogRepository.save(habitLog);

        return habitMapper.toDto(habit);
    }

    public void delete(Long id) {
        if (habitRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Habit with this id does not exist");
        }
        habitRepository.deleteById(id);
    }


}
