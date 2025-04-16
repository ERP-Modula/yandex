package modula.platform.yandex.tracker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueTypeConfig {
    private String issueType;         // Ключ типа задачи
    private String workflow;          // Идентификатор рабочего процесса
    private List<String> resolutions; // Список ключей возможных резолюций
}

