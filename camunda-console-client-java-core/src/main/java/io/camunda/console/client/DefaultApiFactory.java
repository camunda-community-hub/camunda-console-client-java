package io.camunda.console.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.console.client.api.DefaultApi;
import io.camunda.console.client.auth.JsonClientResponseHandler;
import io.camunda.console.client.auth.TokenRequestInterceptor;
import io.camunda.console.client.invoker.ApiClient;
import io.camunda.console.client.properties.CamundaConsoleClientProperties;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpRequestInterceptor;

public class DefaultApiFactory {
  private final CamundaConsoleClientProperties properties;
  private final ObjectMapper objectMapper;
  private DefaultApi api;

  private DefaultApiFactory(CamundaConsoleClientProperties properties, ObjectMapper objectMapper) {
    this.properties = properties;
    this.objectMapper = objectMapper;
  }

  public static DefaultApiFactory getInstance(CamundaConsoleClientProperties properties) {
    return getInstance(properties, new ObjectMapper());
  }

  public static DefaultApiFactory getInstance(
      CamundaConsoleClientProperties properties, ObjectMapper objectMapper) {
    return new DefaultApiFactory(properties, objectMapper);
  }

  public DefaultApi get() {
    ensureApiClient();
    return api;
  }

  private void ensureApiClient() {
    if (api == null) {
      HttpRequestInterceptor interceptor =
          new TokenRequestInterceptor(properties, new JsonClientResponseHandler(objectMapper));
      CloseableHttpClient httpClient =
          HttpClients.custom().addRequestInterceptorFirst(interceptor).build();
      ApiClient apiClient = new ApiClient(httpClient);
      apiClient.setBasePath(properties.baseUrl());
      api = new DefaultApi(apiClient);
    }
  }
}
