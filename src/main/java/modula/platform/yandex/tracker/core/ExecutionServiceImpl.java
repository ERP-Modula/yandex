package modula.platform.yandex.tracker.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modula.common.domain.workflow.execution.events.IntegrationTask;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.YandexTrackerModule;
import modula.platform.yandex.tracker.common.BaseIntegrationModule;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExecutionServiceImpl implements ExecutionService{
    private final ObjectMapper objectMapper = new ObjectMapper();
    BaseIntegrationModule yandexTrackerModule = new YandexTrackerModule();

    @Transactional
    public void executeCommand(IntegrationTask task) {
        Object object = yandexTrackerModule.executeAction(task.getActionName(), task.getParams());
        System.out.println(object);
    }

    @Transactional
    private void subscribeOnWebhook(){

    }

    private <T> T mapParamsToActionArgument(Map<String, String> params, Class<T> c) {
        return objectMapper.convertValue(params, c);
    }
}
