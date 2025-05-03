package modula.platform.yandex.tracker.token.service;

import com.modula.common.connections.dto.connection.impl.OAuth2ExternalConnectionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modula.platform.yandex.tracker.token.client.CoreBuilderConnectionClient;
//import modula.platform.yandex.tracker.token.client.YandexTokenClient;
import org.springframework.stereotype.Service;
//import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

//    private final YandexTokenClient yandexTokenClient;
//    private final YandexTokenStorageService tokenStorageService;
    private final CoreBuilderConnectionClient coreBuilderConnectionClient;

//    /**
//     * Exchanges the authorization code for a token and stores it.
//     *
//     * @param code The authorization code received from Yandex.
//     * @return A Mono that completes when the token has been exchanged and stored.
//     */
//    public Mono<Void> exchangeAndStoreToken(String code) {
//        return yandexTokenClient.exchangeCodeForToken(code)
//                .doOnNext(token -> {
//                    log.info("Token received from Yandex: {}", token.getTokenType());
//                    tokenStorageService.save(token);
//                })
//                .doOnError(e -> log.error("Error while retrieving token", e))
//                .then();
//    }

    /**
     * Retrieves the token by user email using a connection ID.
     *
     * @param connectionId The unique identifier of the connection.
     * @return The access token associated with the given connection.
     */
    public String getTokenByUserEmail(UUID connectionId) {
        OAuth2ExternalConnectionDto externalConnectionDto =
                (OAuth2ExternalConnectionDto) coreBuilderConnectionClient.getConnection(connectionId);
        return externalConnectionDto.getAccessToken();
    }
}




