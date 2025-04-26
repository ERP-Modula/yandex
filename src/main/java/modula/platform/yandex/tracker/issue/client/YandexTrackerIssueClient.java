package modula.platform.yandex.tracker.issue.client;

import modula.platform.yandex.tracker.common.YandexTrackerBaseClient;
import modula.platform.yandex.tracker.config.YandexTrackerProperties;
import modula.platform.yandex.tracker.issue.domain.Issue;
import modula.platform.yandex.tracker.issue.dto.IssueCreateRequest;
import modula.platform.yandex.tracker.issue.dto.IssueFindRequest;
import modula.platform.yandex.tracker.issue.dto.IssuePatchRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class YandexTrackerIssueClient extends YandexTrackerBaseClient {
    private final YandexTrackerProperties properties;

    public YandexTrackerIssueClient(RestTemplate restTemplate, YandexTrackerProperties properties){
        super(restTemplate);
        this.properties = properties;
        this.token = properties.getToken();
    }

    public Issue createIssue(String orgId, IssueCreateRequest request){
        String url = properties.getBaseUrl() + "/issues";
        return exchange(
                url,
                HttpMethod.POST,
                orgId,
                request,
                Issue.class
        );
    }

    public Issue updateIssue(String orgId, String issueId, IssuePatchRequest request){
        String url = properties.getBaseUrl() + "/issues/" + issueId;

        return exchange(
                url,
                HttpMethod.PATCH,
                orgId,
                request,
                Issue.class
        );
    }

    public Issue moveIssue(String orgId, String issueId, String queueId, IssuePatchRequest request){
        String url = properties.getBaseUrl() + "/issues/" + issueId + "/" + "_move?queue=" + queueId;

        return exchange(
                url,
                HttpMethod.POST,
                orgId,
                request,
                Issue.class
        );
    }

    public List<Issue> findIssue(String orgId, IssueFindRequest request){
        String url = "https://api.tracker.yandex.net/v2" + "/issues/_search?expand=transitions";

        return exchange(
                url,
                HttpMethod.POST,
                orgId,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
    }
}
