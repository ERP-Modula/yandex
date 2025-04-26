package modula.platform.yandex.tracker.issue.dto;

import lombok.Data;

@Data
public class IssuePatchRequest {
    private String summary;
    private String description;
}
