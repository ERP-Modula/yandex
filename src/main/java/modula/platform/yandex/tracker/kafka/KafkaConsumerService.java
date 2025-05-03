package modula.platform.yandex.tracker.kafka;

import com.modula.common.domain.workflow.execution.events.IntegrationTask;
import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.core.ExecutionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ExecutionService executionService;

    @KafkaListener(
            id = "consumer-group-2",
            //TODO вторая часть имени топика (совпадает с названием приложения в конфигурации в БД)
            topics = "integration-task-" + "yandexTracker"
    )
    public void handleTask(@Payload IntegrationTask task) {
        executionService.executeCommand(task);
    }

}
