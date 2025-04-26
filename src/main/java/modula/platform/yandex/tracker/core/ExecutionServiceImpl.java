package modula.platform.yandex.tracker.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modula.common.domain.moduleconfiguration.ModuleAction;
import com.modula.common.domain.moduleconfiguration.ModuleConfiguration;
import com.modula.common.domain.moduleconfiguration.OutputInterface;
import com.modula.common.domain.workflow.execution.IntegrationOutputObject;
import com.modula.common.domain.workflow.execution.OutputInterfaceField;
import com.modula.common.domain.workflow.execution.events.IntegrationTask;
import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.core.util.ObjectToOutputInterfaceFieldMapper;
import modula.platform.yandex.tracker.kafka.KafkaProducerService;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExecutionServiceImpl implements ExecutionService{
    private final KafkaProducerService kafkaProducerService;
    private final ApiManagerService apiManagerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void executeCommand(IntegrationTask task) {

    }

    private <T> T mapParamsToActionArgument(Map<String, String> params, Class<T> c) {
        return objectMapper.convertValue(params, c);
    }

    private <C> IntegrationOutputObject mapOutToIntegrationOutputObject(List<C> out, String actionName) throws InvocationTargetException, IllegalAccessException {
        ModuleConfiguration configuration = apiManagerService.getModuleInfo();
        ModuleAction action = configuration.getActions().stream().filter(a -> a.getName().equals(actionName)).findFirst().orElseThrow();
        List<OutputInterface> interfaces = action.getOutputInterface();

        IntegrationOutputObject integrationOutputObject = new IntegrationOutputObject();
        integrationOutputObject.setModuleName(configuration.getName());
        List<OutputInterfaceField> fields = new ArrayList<>();

        for (Object o: out) {
            List<OutputInterfaceField> mapped = ObjectToOutputInterfaceFieldMapper.mapObjectToFields(interfaces, o, true);
            fields.addAll(mapped);
        }

        integrationOutputObject.setFields(fields);

        return integrationOutputObject;
    }
}
