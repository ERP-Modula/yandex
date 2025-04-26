package modula.platform.yandex.tracker.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yandex.tracker.oauth")
@Getter
@Setter
public class YandexOAuthProperties {
    private String clientId;
    private String clientSecret;
}
