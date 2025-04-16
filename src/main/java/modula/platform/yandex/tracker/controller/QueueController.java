package modula.platform.yandex.tracker.controller;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.domain.Queue;
import modula.platform.yandex.tracker.domain.QueueCreateRequest;
import modula.platform.yandex.tracker.service.QueueServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/yandex-tracker/api/queue/")
@RequiredArgsConstructor
public class QueueController {
    private final QueueServiceImpl queueService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Queue>> getAllQueues(@PathVariable UUID userId) {
        List<Queue> queues = queueService.getAllQueues(userId);
        return ResponseEntity.ok(queues);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Queue> createQueue(
            @PathVariable UUID userId,
            @RequestBody QueueCreateRequest request
    ) {
        Queue createdQueue = queueService.createQueue(userId, request);
        return ResponseEntity.ok(createdQueue);
    }

    @DeleteMapping("/{userId}/{queueId}")
    public ResponseEntity<Void> deleteQueue(
            @PathVariable UUID userId,
            @PathVariable String queueId
    ) {
        queueService.deleteQueue(userId, queueId);
        return ResponseEntity.noContent().build(); // 204
    }
}