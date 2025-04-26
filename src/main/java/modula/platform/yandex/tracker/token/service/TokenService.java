package modula.platform.yandex.tracker.token.service;

import com.modula.common.connections.dto.connection.impl.OAuth2ExternalConnectionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modula.platform.yandex.tracker.token.client.CoreBuilderConnectionClient;
import modula.platform.yandex.tracker.token.client.YandexTokenClient;
import modula.platform.yandex.tracker.token.exception.TokenNotReceivedException;
import modula.platform.yandex.tracker.token.exception.TokenProcessingException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    private final YandexTokenClient yandexTokenClient;
    private final YandexTokenStorageService tokenStorageService;
    private final CoreBuilderConnectionClient coreBuilderConnectionClient;

    public Mono<Void> exchangeAndStoreToken(String code) {
        return yandexTokenClient.exchangeCodeForToken(code)
                .doOnNext(token -> {
                    log.info("Токен получен от Яндекса: {}", token.getTokenType());
                    tokenStorageService.save(token);
                })
                .doOnError(e -> log.error("Ошибка при получении токена", e))
                .then();
    }

    public String getTokenBy(UUID connectionId) {
        OAuth2ExternalConnectionDto externalConnectionDto = (OAuth2ExternalConnectionDto) coreBuilderConnectionClient.getConnection(connectionId);
        return externalConnectionDto.getAccessToken();
    }
}



