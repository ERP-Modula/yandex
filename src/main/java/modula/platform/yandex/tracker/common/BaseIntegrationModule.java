package modula.platform.yandex.tracker.common;

import com.modula.common.connections.dto.connection.impl.OAuth2ExternalConnectionDto;
import com.modula.common.domain.moduleconfiguration.AuthType;
import com.modula.common.domain.moduleconfiguration.ModuleAction;
import com.modula.common.domain.moduleconfiguration.ModuleConfiguration;
import com.modula.common.utils.ProviderUrlManager;
import modula.platform.yandex.tracker.core.repository.ModuleConfigurationRepository;
import modula.platform.yandex.tracker.token.client.CoreBuilderConnectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public abstract class BaseIntegrationModule {

//    @Autowired
//    protected KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    protected ModuleConfigurationRepository configRepository;
    @Autowired
    protected ProviderUrlManager providerUrlManager;
    @Autowired
    protected CoreBuilderConnectionClient coreBuilderConnectionClient;
    protected final String moduleName;

    protected BaseIntegrationModule(String moduleName) {
        this.moduleName = moduleName;
    }

    public Object executeAction(String actionName, Map<String, Object> actionRequestParams) {
        RestTemplate restTemplate = new RestTemplate();
//        TODO handle custom exception
        var moduleConfiguration = configRepository.findByName(moduleName)
                .orElseThrow(() -> new IllegalStateException("Module config not found"));
        ModuleAction action = moduleConfiguration.getActions().stream().filter(moduleAction -> moduleAction.getName().equals(actionName)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Action not found"));

        // 1. Строим динамический запрос
//        Object requestBody = DynamicRequestBuilder.buildRequest(
//                action.getInputParameters(),
//                params
//        );

        // 2. Подготавливаем URL с параметрами
        String url = resolveActionEndpointUrl(moduleConfiguration.getRestApiBaseUrl(), action.getEndpointUrl(), actionRequestParams);

        var requestBody = new HashMap<>(actionRequestParams);
        requestBody.putAll(extendRequestBodyParams(actionRequestParams));
        requestBody.remove("connectionId");
        System.out.println("Request body");
        System.out.println(requestBody);
        // 3. Выполняем запрос
        HttpEntity<HashMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, prepareHeaders(moduleConfiguration, actionRequestParams));
        System.out.println("Request entity");
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.valueOf(String.valueOf(action.getMethodType())),
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );
        System.out.println(response.toString());
        // 4. Обрабатываем ответ
        return response;
//        return processResponse(response.getBody(), action.getOutputInterface());
    }

    private String resolveActionEndpointUrl(String restApiBaseUrl, String actionEndpointUrl, Map<String, Object> actionRequestParams) {

        var baseUri = providerUrlManager.resolveUriTemplate(restApiBaseUrl, getConnectionParam(actionRequestParams));
        var actionEndpointUriTemplate = "${baseUri}/${actionEndpointUrl}";
        return providerUrlManager.resolveUriTemplate(actionEndpointUriTemplate, Map.of(
                "baseUri", baseUri,
                "actionEndpointUrl", actionEndpointUrl
        ));
    }

    public abstract Map<String, String> extendRequestBodyParams(Map<String, Object> actionRequestParams);

    protected String getOauth2AccessToken(Map<String, Object> params) {
        var connectionId = getConnectionId(params);
        var externalConnectionDto = (OAuth2ExternalConnectionDto) coreBuilderConnectionClient.getConnection(UUID.fromString(connectionId));
        return externalConnectionDto.getAccessToken();
    }

    private Map<String, String> getConnectionParam(Map<String, Object> params) {
        var connectionId = getConnectionId(params);
        var externalConnectionDto = (OAuth2ExternalConnectionDto) coreBuilderConnectionClient.getConnection(UUID.fromString(connectionId));
        return externalConnectionDto.getConnectionAdditionalParams();
    }

    //    TODO должны переопределяться
    private HttpHeaders prepareHeaders(ModuleConfiguration moduleConfiguration, Map<String, Object> actionRequestParams) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Добавляем OAuth токен из контекста
        if (moduleConfiguration.getAuthType() == AuthType.OAUTH2) {
            headers.setBearerAuth(getOauth2AccessToken(actionRequestParams));
        }

        System.out.println("Action headers");
        System.out.println(headers.toString());
        return headers;
    }

    private static String getConnectionId(Map<String, Object> params) {
        String connectionId = params.get("connectionId").toString();

        System.out.println("connectionId");
        System.out.println(connectionId);
        if (connectionId == null) {
            throw new RuntimeException("connection not specified");
        }
        return connectionId;
    }


}
