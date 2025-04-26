package modula.platform.yandex.tracker.queue.service;

import modula.platform.yandex.tracker.queue.domain.Queue;
import modula.platform.yandex.tracker.queue.dto.QueueCreateRequest;

import java.util.List;
import java.util.UUID;

public interface QueueService {
    List<Queue> getAllQueues(String orgID);
    Queue createQueue(String orgID, QueueCreateRequest request);
    void deleteQueue(String orgID, String queueId);
}
