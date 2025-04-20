package modula.platform.yandex.tracker.domain;

import lombok.Data;

@Data
public class IssuePatchRequest {
    private String summary;
    private String description;
}
