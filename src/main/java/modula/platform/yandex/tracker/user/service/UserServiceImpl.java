package modula.platform.yandex.tracker.user.service;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.user.client.YandexTrackerUserClient;
import modula.platform.yandex.tracker.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final YandexTrackerUserClient yandexTrackerClient;

    @Override
    public User getCurrentUser(String orgId) {
        return yandexTrackerClient.fetchCurrentUser(orgId);
    }
}


