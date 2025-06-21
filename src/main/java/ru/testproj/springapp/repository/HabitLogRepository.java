package ru.testproj.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testproj.springapp.entity.Habit;
import ru.testproj.springapp.entity.HabitLog;

import java.time.LocalDateTime;
import java.util.List;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    HabitLog findByHabitId(Long habitId);
    List<HabitLog> findAllByUpdateDateBetween(LocalDateTime start, LocalDateTime end);
}
