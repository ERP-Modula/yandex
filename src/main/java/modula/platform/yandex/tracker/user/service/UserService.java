package modula.platform.yandex.tracker.user.service;

import modula.platform.yandex.tracker.user.domain.User;

import java.util.UUID;

public interface UserService {
    User getCurrentUser(String userId);
}


