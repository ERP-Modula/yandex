package modula.platform.yandex.tracker.service;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.client.YandexTrackerQueueClient;
import modula.platform.yandex.tracker.domain.Client;
import modula.platform.yandex.tracker.domain.Queue;
import modula.platform.yandex.tracker.domain.QueueCreateRequest;
import modula.platform.yandex.tracker.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {
    private final YandexTrackerQueueClient yandexTrackerQueueClient;
    private final ClientRepository clientRepository;

    @Override
    public List<Queue> getAllQueues(UUID userId) {
        Client client = clientRepository.findByModulaId(userId)
                .orElseThrow(() -> new RuntimeException("Organization id not found for user id: " + userId));

        return yandexTrackerQueueClient.fetchAllQueues(client.getOrgId());
    }

    @Override
    public Queue createQueue(UUID userId, QueueCreateRequest request) {
        Client client = clientRepository.findByModulaId(userId)
                .orElseThrow(() -> new RuntimeException("Organization id not found for user id: " + userId));

        return yandexTrackerQueueClient.createQueue(client.getOrgId(), request);
    }

    @Override
    public void deleteQueue(UUID userId, String queueId) {
        Client client = clientRepository.findByModulaId(userId)
                .orElseThrow(() -> new RuntimeException("Organization id not found for user id: " + userId));

        yandexTrackerQueueClient.deleteQueue(client.getOrgId(), queueId);
    }

}
