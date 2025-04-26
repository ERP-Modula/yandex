package modula.platform.yandex.tracker.queue.controller;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.queue.domain.Queue;
import modula.platform.yandex.tracker.queue.dto.QueueCreateRequest;
import modula.platform.yandex.tracker.queue.service.QueueServiceImpl;
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
    public ResponseEntity<List<Queue>> getAllQueues(@PathVariable String orgID) {
        List<Queue> queues = queueService.getAllQueues(orgID);
        return ResponseEntity.ok(queues);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Queue> createQueue(
            @PathVariable String orgID,
            @RequestBody QueueCreateRequest request
    ) {
        Queue createdQueue = queueService.createQueue(orgID, request);
        return ResponseEntity.ok(createdQueue);
    }

    @DeleteMapping("/{userId}/{queueId}")
    public ResponseEntity<Void> deleteQueue(
            @PathVariable String orgID,
            @PathVariable String queueId
    ) {
        queueService.deleteQueue(orgID, queueId);
        return ResponseEntity.noContent().build(); // 204
    }
}