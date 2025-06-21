package ru.testproj.springapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.testproj.springapp.dto.habitlog.HabitLogChangeStatusDto;
import ru.testproj.springapp.dto.habitlog.HabitLogDto;
import ru.testproj.springapp.service.HabitLogService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/habits")
public class HabitLogController {
    private final HabitLogService habitLogService;

    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }

    @PostMapping(path = "{habitId}/log")
    public HabitLogDto changeStatus(@PathVariable Long habitId, @RequestBody HabitLogChangeStatusDto habitLogChangeStatusDto) {
        habitLogChangeStatusDto.setHabit_id(habitId);
        return habitLogService.changeStatus(habitLogChangeStatusDto);
    }

    @GetMapping("/log")
    public List<HabitLogDto> getHabits(
            @RequestParam("from") LocalDate fromDate,
            @RequestParam("to") LocalDate toDate
    ) {
        return habitLogService.getHabitsFromPeriod(fromDate,toDate);
    }
}
