package modula.platform.yandex.tracker.token.mapper;

import modula.platform.yandex.tracker.token.domain.YandexToken;
import modula.platform.yandex.tracker.token.dto.YandexTokenResponse;

public class YandexTokenMapper {
    public static YandexToken toEntity(YandexTokenResponse response) {
        YandexToken entity = new YandexToken();
        entity.setAccessToken(response.getAccessToken());
        entity.setRefreshToken(response.getRefreshToken());
        entity.setTokenType(response.getTokenType());
        entity.setExpiresIn(response.getExpiresIn());
        return entity;
    }
}

