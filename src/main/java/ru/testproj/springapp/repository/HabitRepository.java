package ru.testproj.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testproj.springapp.entity.Habit;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    Habit findByName(String name);

    List<Habit> findAllByUserId(Long userId);
}
