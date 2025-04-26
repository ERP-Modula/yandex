package modula.platform.yandex.tracker.core;

import com.modula.common.domain.workflow.execution.events.IntegrationTask;

public interface ExecutionService {
    void executeCommand(IntegrationTask task);
}
