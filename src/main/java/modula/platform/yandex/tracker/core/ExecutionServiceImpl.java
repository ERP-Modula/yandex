package modula.platform.yandex.tracker.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modula.common.domain.workflow.execution.events.IntegrationTask;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExecutionServiceImpl implements ExecutionService{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public void executeCommand(IntegrationTask task) {
        String actionName = task.getActionName();
//        Map<String, String> params = task.getParams();
//        IntegrationOutputObject outputObject = new IntegrationOutputObject();
//
//        try {
//            switch (actionName) {
//
//                //TODO cases by action name
//                case "listFiles":
//                    // for example
////                    List<File> out = googleDocsService.getDocumentsFromFolder(mapParamsToActionArgument(params, ListDocumentsRequest.class));
////                    outputObject = mapOutToIntegrationOutputObject(out, actionName);
//                    break;
//                default:
//                    throw new IllegalArgumentException("action with name \"" + actionName + "\" doesn't exist");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        outputObject.setStepId(task.getStepId());
//
//        ExecutorTask executorTask = ExecutorTask.builder()
//                .workflowInstanceId(task.getWorkflowInstanceId())
//                .isFirstStep(false)
//                .integrationOutput(outputObject)
//                .build();
//
//        kafkaProducerService.sendIntegrationOutputResult(executorTask);
    }
    @Transactional
    private void subscribeOnWebhook(){

    }

    private <T> T mapParamsToActionArgument(Map<String, String> params, Class<T> c) {
        return objectMapper.convertValue(params, c);
    }

//    private <C> IntegrationOutputObject mapOutToIntegrationOutputObject(List<C> out, String actionName) throws InvocationTargetException, IllegalAccessException {
//        ModuleConfiguration configuration = apiManagerService.getModuleInfo();
//        ModuleAction action = configuration.getActions().stream().filter(a -> a.getName().equals(actionName)).findFirst().orElseThrow();
//        List<OutputInterface> interfaces = action.getOutputInterface();
//
//        IntegrationOutputObject integrationOutputObject = new IntegrationOutputObject();
//        integrationOutputObject.setModuleName(configuration.getName());
//        List<OutputInterfaceField> fields = new ArrayList<>();
//
//        for (Object o: out) {
//            List<OutputInterfaceField> mapped = ObjectToOutputInterfaceFieldMapper.mapObjectToFields(interfaces, o, true);
//            fields.addAll(mapped);
//        }
//
//        integrationOutputObject.setFields(fields);
//
//        return integrationOutputObject;
//    }
}
