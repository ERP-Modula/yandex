package modula.platform.yandex.tracker.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class YandexTrackerBaseClient {

    @Value("${yandex.tracker.api.token}")
    protected String token;

    protected final RestTemplate restTemplate;

    protected YandexTrackerBaseClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected HttpHeaders buildHeaders(String orgId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "OAuth " + token);
        headers.set("Content-Type", "application/json");
        headers.set("X-Org-ID", orgId);
        return headers;
    }

    protected <T> T exchange(
            String url,
            HttpMethod method,
            String orgId,
            Object body,
            Class<T> responseType
    ) {
        HttpEntity<Object> entity = new HttpEntity<>(body, buildHeaders(orgId));

        ResponseEntity<T> response = restTemplate.exchange(
                url,
                method,
                entity,
                responseType
        );

        return response.getBody();
    }

    protected <T> T exchange(
            String url,
            HttpMethod method,
            String orgId,
            Object body,
            ParameterizedTypeReference<T> responseType
    ) {
        HttpEntity<Object> entity = new HttpEntity<>(body, buildHeaders(orgId));

        ResponseEntity<T> response = restTemplate.exchange(
                url,
                method,
                entity,
                responseType
        );

        return response.getBody();
    }

}

