package ru.testproj.springapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.testproj.springapp.dto.user.UserCreateDto;
import ru.testproj.springapp.dto.user.UserDto;
import ru.testproj.springapp.dto.user.UserUpdateDto;
import ru.testproj.springapp.entity.User;
import ru.testproj.springapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> users(){
        return userService.findAll();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserCreateDto user) {
        return userService.create(user);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping(path = "{id}")
    public UserDto update(
            @PathVariable Long id,
            @RequestBody UserUpdateDto dto) {
        return userService.update(id, dto);
    }
}
