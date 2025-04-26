package modula.platform.yandex.tracker.token.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modula.platform.yandex.tracker.config.YandexOAuthProperties;
import modula.platform.yandex.tracker.token.domain.YandexToken;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class YandexTokenClient {

    private final WebClient yandexWebClient;
    private final YandexOAuthProperties oauthProperties;

    public Mono<YandexToken> exchangeCodeForToken(String code) {
        return yandexWebClient.post()
                .uri("/token")
                .body(BodyInserters.fromFormData("grant_type", "authorization_code")
                        .with("code", code)
                        .with("client_id", oauthProperties.getClientId())
                        .with("client_secret", oauthProperties.getClientSecret()))
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(body -> {
                                    log.error("Ошибка от Яндекса: [{}] {}", clientResponse.statusCode(), body);
                                    return Mono.error(new RuntimeException("Ошибка при получении токена: " + body));
                                })
                )
                .bodyToMono(YandexToken.class);
    }
}

