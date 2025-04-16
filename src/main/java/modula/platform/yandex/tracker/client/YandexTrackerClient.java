package modula.platform.yandex.tracker.client;

import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class YandexTrackerClient {

    private final RestTemplate restTemplate;

    @Value("${yandex.tracker.api.token}")
    private String token;

    public User fetchCurrentUser(String orgId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "OAuth " + token);
        headers.set("Content-Type", "application/json");
        headers.set("X-Org-ID", orgId);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange(
                "https://api.tracker.yandex.net/v2/myself", // Используем endpoint /v2/myself
                HttpMethod.GET,
                entity,
                User.class
        );

        return response.getBody();
    }
}


