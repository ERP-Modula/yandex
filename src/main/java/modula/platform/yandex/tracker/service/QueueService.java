package modula.platform.yandex.tracker.service;

import modula.platform.yandex.tracker.domain.Queue;
import modula.platform.yandex.tracker.domain.QueueCreateRequest;

import java.util.List;
import java.util.UUID;

public interface QueueService {
    List<Queue> getAllQueues(UUID userId);
    Queue createQueue(UUID userId, QueueCreateRequest request);
    void deleteQueue(UUID userId, String queueId);
}
