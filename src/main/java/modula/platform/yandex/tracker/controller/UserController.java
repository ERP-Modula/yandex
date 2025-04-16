package modula.platform.yandex.tracker.controller;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.domain.User;
import modula.platform.yandex.tracker.service.UserService;
import modula.platform.yandex.tracker.service.UserServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/yandex-tracker/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me/{userId}")
    public ResponseEntity<User> getCurrentUser(@PathVariable UUID userId) {
        User currentUser = userService.getCurrentUser(userId);
        return ResponseEntity.ok(currentUser);
    }
}



