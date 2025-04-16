package modula.platform.yandex.tracker.service;

import modula.platform.yandex.tracker.domain.User;

import java.util.UUID;

public interface UserService {
    User getCurrentUser(UUID userId);
}


