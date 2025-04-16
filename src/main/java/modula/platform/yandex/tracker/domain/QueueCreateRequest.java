package modula.platform.yandex.tracker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueueCreateRequest {
    private String key;                    // Ключ очереди
    private String name;                   // Название очереди
    private String lead;                   // Логин или ID владельца
    private String defaultType;            // Тип задачи по умолчанию
    private String defaultPriority;        // Приоритет по умолчанию
    private List<IssueTypeConfig> issueTypesConfig; // Массив конфигураций типов задач
}

