//package modula.platform.yandex.tracker.queue.service;
//
//import lombok.RequiredArgsConstructor;
//import modula.platform.yandex.tracker.queue.client.YandexTrackerQueueClient;
//import modula.platform.yandex.tracker.queue.domain.Queue;
//import modula.platform.yandex.tracker.queue.dto.QueueCreateRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class QueueServiceImpl implements QueueService {
//    private final YandexTrackerQueueClient yandexTrackerQueueClient;
//
//    @Override
//    public List<Queue> getAllQueues(String orgId) {
//        return yandexTrackerQueueClient.fetchAllQueues(orgId);
//    }
//
//    @Override
//    public Queue createQueue(String orgID, QueueCreateRequest request) {
//        return yandexTrackerQueueClient.createQueue(orgID, request);
//    }
//
//    @Override
//    public void deleteQueue(String orgID, String queueId) {
//        yandexTrackerQueueClient.deleteQueue(orgID, queueId);
//    }
//
//}
