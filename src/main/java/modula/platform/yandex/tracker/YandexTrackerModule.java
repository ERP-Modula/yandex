package modula.platform.yandex.tracker;

import modula.platform.yandex.tracker.common.BaseIntegrationModule;

import java.util.Map;

public class YandexTrackerModule extends BaseIntegrationModule {
    public YandexTrackerModule() {
        super("yandexTracker");
    }

    @Override
    public Map<String, String> extendRequestBodyParams(Map<String, Object> actionRequestParams) {
        var oauth2AccessToken = this.getOauth2AccessToken(actionRequestParams);
        return Map.of(
                "auth", oauth2AccessToken
        );
    }
}
