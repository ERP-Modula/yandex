package modula.platform.yandex.tracker.service;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.client.YandexTrackerUserClient;
import modula.platform.yandex.tracker.domain.Client;
import modula.platform.yandex.tracker.domain.User;
import modula.platform.yandex.tracker.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final YandexTrackerUserClient yandexTrackerClient;
    private final ClientRepository clientRepository;

    @Override
    public User getCurrentUser(UUID userId) {
        // Например, находим org_id в базе данных по идентификатору пользователя
        Client client = clientRepository.findByModulaId(userId)
                .orElseThrow(() -> new RuntimeException("Organization id not found for user id: " + userId));

        return yandexTrackerClient.fetchCurrentUser(client.getOrgId());
    }
}


