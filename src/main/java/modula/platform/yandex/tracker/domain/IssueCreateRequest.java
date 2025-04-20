package modula.platform.yandex.tracker.domain;

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
