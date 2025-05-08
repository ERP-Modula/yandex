//package modula.platform.yandex.tracker.user.client;
//
//import modula.platform.yandex.tracker.common.YandexTrackerBaseClient;
//import modula.platform.yandex.tracker.user.domain.User;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//@Component
//public class YandexTrackerUserClient extends YandexTrackerBaseClient {
//
//    private final YandexTrackerProperties properties;
//
//    public YandexTrackerUserClient(RestTemplate restTemplate, YandexTrackerProperties properties) {
//        super(restTemplate);
//        this.properties = properties;
//        this.token = properties.getToken();
//    }
//
//    public User fetchCurrentUser(String orgId) {
//        String url = properties.getBaseUrl() + "/myself";
//        return exchange(url, HttpMethod.GET, orgId, null, User.class);
//    }
//}
//
//
//
