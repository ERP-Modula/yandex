package modula.platform.yandex.tracker.queue.client;

import modula.platform.yandex.tracker.common.YandexTrackerBaseClient;
import modula.platform.yandex.tracker.config.YandexTrackerProperties;
import modula.platform.yandex.tracker.queue.domain.Queue;
import modula.platform.yandex.tracker.queue.dto.QueueCreateRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class YandexTrackerQueueClient extends YandexTrackerBaseClient {

    private final YandexTrackerProperties properties;

    public YandexTrackerQueueClient(RestTemplate restTemplate, YandexTrackerProperties properties) {
        super(restTemplate);
        this.properties = properties;
        this.token = properties.getToken();
    }

    public List<Queue> fetchAllQueues(String orgId) {
        String url = properties.getBaseUrl() + "/queues";
        return exchange(
                url,
                HttpMethod.GET,
                orgId,
                null,
                new ParameterizedTypeReference<List<Queue>>() {}
        );
    }

    public Queue createQueue(String orgId, QueueCreateRequest request) {
        String url = properties.getBaseUrl() + "/queues";
        return exchange(
                url,
                HttpMethod.POST,
                orgId,
                request,
                Queue.class
        );
    }

    public void deleteQueue(String orgId, String queueId) {
        String url = properties.getBaseUrl() + "/queues/" + queueId;

        exchange(
                url,
                HttpMethod.DELETE,
                orgId,
                null,
                Void.class
        );
    }
}

