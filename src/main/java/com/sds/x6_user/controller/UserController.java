package com.sds.x6_user.controller;

import com.sds.x6_user.model.User;
import com.sds.x6_user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
@Tag(name = "Пользователь")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Создать пользователя")
    public User create(@RequestBody final User user) {
        return userService.create(user);
    }

    @GetMapping("/all")
    @Operation(summary = "Получить всех пользователей")
    public List<User> getById() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}/exists")
    public boolean isUserAvailable(@PathVariable("id") Long id) {
        return userService.isAvailable(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя")
    public User getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновить пользователя")
    public User update(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
}
