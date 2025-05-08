package modula.platform.yandex.tracker.common;

import com.modula.common.connections.dto.connection.impl.OAuth2ExternalConnectionDto;
import com.modula.common.domain.moduleconfiguration.AuthType;
import com.modula.common.domain.moduleconfiguration.ModuleAction;
import com.modula.common.domain.moduleconfiguration.ModuleConfiguration;
import com.modula.common.utils.ProviderUrlManager;
import modula.platform.yandex.tracker.core.repository.ModuleConfigurationRepository;
import modula.platform.yandex.tracker.token.client.CoreBuilderConnectionClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    protected static final Logger log = LoggerFactory.getLogger(BaseIntegrationModule.class);

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

    /**
     * Executes an action by making an HTTP request based on the module configuration and action parameters.
     *
     * This method performs the following steps:
     * 1. Retrieves the module configuration based on the module name.
     * 2. Finds the corresponding action by its name.
     * 3. Constructs the request URL using the module's base URL and action-specific endpoint.
     * 4. Prepares the request entity, including headers and body.
     * 5. Executes the HTTP request using RestTemplate.
     * 6. Processes the response and returns the result.
     *
     * If any step fails, the method catches the exception, logs the error, and throws a RuntimeException.
     *
     * @param actionName The name of the action to be executed.
     * @param actionRequestParams A map containing request parameters, including headers, body fields, and connection details.
     * @return The response from the external API as a generic object.
     * @throws RuntimeException if the action execution fails at any step.
     */
    public Object executeAction(String actionName, Map<String, Object> actionRequestParams) {
        try {
            // Step 1: Retrieve the module configuration
            var moduleConfiguration = getModuleConfiguration();
            var action = getAction(moduleConfiguration, actionName);

            // Step 2: Construct the URL with parameters
            String url = resolveActionEndpointUrl(
                    moduleConfiguration.getRestApiBaseUrl(),
                    action.getEndpointUrl(),
                    actionRequestParams
            );

            // Step 3: Prepare the request entity (headers and body)
            HttpEntity<HashMap<String, Object>> requestEntity = buildRequestEntity(moduleConfiguration, actionRequestParams);

            // Step 4: Execute the HTTP request using RestTemplate
            ResponseEntity<Map<String, Object>> response = performRequest(url, action, requestEntity);

            // Step 5: Process and return the response
            return processResponse(response);

        } catch (Exception e) {
            // Log the error and rethrow as a RuntimeException
            log.error("Failed to execute action '{}': {}", actionName, e.getMessage());
            throw new RuntimeException("Error executing action: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves the configuration for the current module.
     *
     * This method searches the configuration repository for a module
     * by its name. If no configuration is found, it throws an exception.
     *
     * @return The module configuration object.
     * @throws IllegalStateException if the module configuration is not found.
     */
    private ModuleConfiguration getModuleConfiguration() {
        return configRepository.findByName(moduleName)
                .orElseThrow(() -> new IllegalStateException("Module config not found"));
    }

    /**
     * Retrieves a specific action from the module configuration.
     *
     * This method searches for an action within the provided module configuration
     * based on the given action name. If the action is not found, it throws an exception.
     *
     * @param moduleConfiguration The configuration object containing the module's actions.
     * @param actionName The name of the action to find.
     * @return The corresponding ModuleAction object.
     * @throws IllegalArgumentException if the specified action is not found.
     */
    private ModuleAction getAction(ModuleConfiguration moduleConfiguration, String actionName) {
        return moduleConfiguration.getActions().stream()
                .filter(moduleAction -> moduleAction.getName().equals(actionName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Action not found: " + actionName));
    }

    /**
     * Builds the HTTP request entity, including headers and body.
     *
     * This method constructs the request body from the provided action request parameters.
     * It also extends the body with additional parameters and removes any unnecessary fields
     * (like connection ID). Headers are prepared based on the module configuration.
     *
     * The resulting HTTP entity contains:
     * - The request body (as a HashMap).
     * - The prepared HTTP headers.
     *
     * @param moduleConfiguration The configuration object containing module details.
     * @param actionRequestParams A map of parameters to include in the request.
     * @return An HttpEntity object containing the request body and headers.
     */
    private HttpEntity<HashMap<String, Object>> buildRequestEntity(
            ModuleConfiguration moduleConfiguration,
            Map<String, Object> actionRequestParams) {

        // Copy the initial request parameters
        var requestBody = new HashMap<>(actionRequestParams);

        // Extend the request body with additional parameters if needed
        requestBody.putAll(extendRequestBodyParams(actionRequestParams));

        // Remove the connection ID from the request body, as it is not part of the payload
        requestBody.remove("connectionId");

        // Prepare HTTP headers based on the module configuration
        HttpHeaders headers = prepareHeaders(moduleConfiguration, actionRequestParams);

        // Log the constructed request body and headers for debugging purposes
        log.debug("Request body: {}", requestBody);
        log.debug("Request headers: {}", headers);

        // Return the complete HTTP entity
        return new HttpEntity<>(requestBody, headers);
    }

    /**
     * Performs an HTTP request using RestTemplate and returns the response.
     *
     * This method constructs a new RestTemplate instance and logs the request
     * method type and URL. It then performs the exchange using the given request
     * entity and action details. The response is returned directly.
     *
     * @param url The complete URL to which the request will be sent.
     * @param action The action object containing method type and endpoint information.
     * @param requestEntity The HTTP entity containing headers and body.
     * @return A ResponseEntity object containing the response data from the API.
     * @throws RuntimeException if the HTTP request fails or the response status is not successful.
     */
    private ResponseEntity<Map<String, Object>> performRequest(
            String url,
            ModuleAction action,
            HttpEntity<HashMap<String, Object>> requestEntity) {

        RestTemplate restTemplate = createRestTemplate();
        log.info("Executing {} request to URL: {}", action.getMethodType(), url);

        // Executes the HTTP request using the given method type and URL
        return restTemplate.exchange(
                url,
                HttpMethod.valueOf(String.valueOf(action.getMethodType())),
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );
    }

    /**
     * Creates and configures a RestTemplate instance.
     *
     * This method serves as a factory for RestTemplate objects.
     * It can be extended to include interceptors, error handlers, or timeouts.
     *
     * @return A new instance of RestTemplate.
     */
    private RestTemplate createRestTemplate() {
        return new RestTemplate(); // Placeholder for additional configuration or interceptors
    }

    /**
     * Processes the HTTP response received from the external API.
     *
     * This method checks whether the response status code indicates success
     * (2xx). If so, it logs the response and returns the body. If not, it logs
     * a warning and throws a RuntimeException with the response status.
     *
     * @param response The ResponseEntity object returned from the API.
     * @return The response body as a generic Object if the request was successful.
     * @throws RuntimeException if the response status code is not successful (non-2xx).
     */
    private Object processResponse(ResponseEntity<Map<String, Object>> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Received successful response: {}", response.getBody());
            return response.getBody();
        } else {
            log.warn("Received non-2xx response: {}", response);
            throw new RuntimeException("Request failed with status: " + response.getStatusCode());
        }
    }


    /**
     * Resolves the complete endpoint URL for an API action.
     *
     * This method constructs the final URL by combining the base URL with the action-specific endpoint.
     * It uses the providerUrlManager to resolve URI templates based on the given parameters.
     *
     * The URL is constructed as follows:
     * 1. The base URI is resolved using the base URL and connection parameters.
     * 2. The complete endpoint URL is constructed by concatenating the base URI and the action endpoint.
     * 3. The final URL is resolved by replacing template placeholders with actual values.
     *
     * @param restApiBaseUrl The base URL of the external API.
     * @param actionEndpointUrl The specific endpoint path for the action.
     * @param actionRequestParams A map of parameters required to resolve the URL.
     * @return The resolved endpoint URL as a string.
     */
    private String resolveActionEndpointUrl(String restApiBaseUrl, String actionEndpointUrl, Map<String, Object> actionRequestParams) {
        // Resolve the base URI with connection-specific parameters
        var baseUri = providerUrlManager.resolveUriTemplate(restApiBaseUrl, getConnectionParam(actionRequestParams));

        // Construct the action endpoint template
        var actionEndpointUriTemplate = "${baseUri}/${actionEndpointUrl}";

        // Resolve the final URL using the template and parameter map
        return providerUrlManager.resolveUriTemplate(actionEndpointUriTemplate, Map.of(
                "baseUri", baseUri,
                "actionEndpointUrl", actionEndpointUrl
        ));
    }

    /**
     * Abstract method for extending request body parameters.
     *
     * Subclasses should override this method to add specific parameters
     * required for their API calls. This approach allows customization
     * without changing the base implementation.
     *
     * @param actionRequestParams A map of existing parameters for the request.
     * @return A map of additional parameters to include in the request body.
     */
    public abstract Map<String, String> extendRequestBodyParams(Map<String, Object> actionRequestParams);

    /**
     * Retrieves the OAuth2 access token associated with the given connection ID.
     *
     * This method uses the coreBuilderConnectionClient to fetch the external connection details
     * based on the provided connection ID. It then extracts the OAuth2 access token from the response.
     *
     * @param params A map containing the "connectionId" as a key.
     * @return The OAuth2 access token as a string.
     * @throws RuntimeException if the connection ID is not found or invalid.
     */
    protected String getOauth2AccessToken(Map<String, Object> params) {
        var connectionId = getConnectionId(params);
        var externalConnectionDto = (OAuth2ExternalConnectionDto)
                coreBuilderConnectionClient.getConnection(UUID.fromString(connectionId));
        return externalConnectionDto.getAccessToken();
    }

    /**
     * Retrieves the additional connection parameters associated with the given connection ID.
     *
     * This method queries the coreBuilderConnectionClient for the connection details
     * and returns any additional parameters configured for the OAuth2 connection.
     *
     * @param params A map containing the "connectionId" as a key.
     * @return A map of additional connection parameters.
     * @throws RuntimeException if the connection ID is not found or invalid.
     */
    private Map<String, String> getConnectionParam(Map<String, Object> params) {
        var connectionId = getConnectionId(params);
        var externalConnectionDto = (OAuth2ExternalConnectionDto)
                coreBuilderConnectionClient.getConnection(UUID.fromString(connectionId));
        return externalConnectionDto.getConnectionAdditionalParams();
    }

    /**
     * Prepares the HTTP headers for the API request.
     *
     * This method constructs a set of HTTP headers based on the module configuration
     * and the provided action request parameters. It sets the content type to JSON
     * and includes an OAuth2 Bearer token if the module requires OAuth2 authentication.
     *
     * The method can be extended in subclasses to include additional headers
     * or override existing ones.
     *
     * @param moduleConfiguration The configuration of the module, containing authentication type.
     * @param actionRequestParams A map containing request-specific parameters.
     * @return A populated HttpHeaders object with the required headers.
     */
    private HttpHeaders prepareHeaders(ModuleConfiguration moduleConfiguration, Map<String, Object> actionRequestParams) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Add OAuth2 token if required by the module configuration
        if (moduleConfiguration.getAuthType() == AuthType.OAUTH2) {
            headers.setBearerAuth(getOauth2AccessToken(actionRequestParams));
        }

        return headers;
    }

    /**
     * Retrieves the connection ID from the action request parameters.
     *
     * This method extracts the connection ID from the provided parameter map.
     * It ensures that the connection ID is not null before returning it.
     *
     * @param params A map containing the "connectionId" key.
     * @return The connection ID as a string.
     * @throws RuntimeException if the connection ID is not specified or is null.
     */
    private static String getConnectionId(Map<String, Object> params) {
        String connectionId = params.get("connectionId").toString();
        if (connectionId == null) {
            throw new RuntimeException("Connection ID not specified");
        }
        return connectionId;
    }
}
