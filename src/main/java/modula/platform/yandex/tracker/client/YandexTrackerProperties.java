package modula.platform.yandex.tracker.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yandex.tracker.api")
@Getter
@Setter
public class YandexTrackerProperties {
    private String baseUrl;
    private String token;
}

