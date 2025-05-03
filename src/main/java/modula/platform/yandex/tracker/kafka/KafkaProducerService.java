package modula.platform.yandex.tracker.kafka;

import com.modula.common.domain.workflow.execution.events.ExecutorTask;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {


    @Value("${kafka.topics.workflow-executor-topic}")
    private String execTopicName;

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void sendIntegrationOutputResult(ExecutorTask executorTask) {
        kafkaTemplate.send(execTopicName, executorTask);
    }

}
