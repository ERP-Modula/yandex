package modula.platform.yandex.tracker.user.controller;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.user.domain.User;
import modula.platform.yandex.tracker.user.service.UserService;

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

    @GetMapping("/myself")
    public ResponseEntity<User> getCurrentUser(@PathVariable String orgID) {
        User currentUser = userService.getCurrentUser(orgID);
        return ResponseEntity.ok(currentUser);
    }
}



