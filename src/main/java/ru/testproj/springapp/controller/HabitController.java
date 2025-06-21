package ru.testproj.springapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.testproj.springapp.dto.habit.HabitCreateDto;
import ru.testproj.springapp.dto.habit.HabitDto;
import ru.testproj.springapp.service.HabitService;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping(path = "{userId}/habits")
    public List<HabitDto> getHabits(@PathVariable Long userId) {
        return habitService.findAllByUserId(userId);
    }

    @PostMapping(path = "{userId}/habits")
    public HabitDto create(@PathVariable Long userId, @RequestBody HabitCreateDto dto) {
        dto.setUserId(userId);
        return habitService.create(dto);
    }

    @DeleteMapping(path = "/habits/{habitId}")
    public void delete(@PathVariable Long habitId) {
        habitService.delete(habitId);
    }
}
