package modula.platform.yandex.tracker.issue.dto;

import lombok.Data;

@Data
public class IssueCreateRequest {
    private String summary;
    private Queue queue;

    @Data
    public static class Queue {
        private String id;
        private String key;
    }
}
