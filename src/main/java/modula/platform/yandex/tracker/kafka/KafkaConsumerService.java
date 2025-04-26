package modula.platform.yandex.tracker.kafka;

import com.modula.common.domain.workflow.execution.events.IntegrationTask;
import lombok.AllArgsConstructor;
import modula.platform.yandex.tracker.core.ExecutionServiceImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumerService {
    private final ExecutionServiceImpl executionService;

    @KafkaListener(
            id = "consumer-group-2",
            topics = "integration-task-" + "yandex-tracker"
    )
    public void handleTask(@Payload IntegrationTask task) {
        executionService.executeCommand(task);
    }
}
