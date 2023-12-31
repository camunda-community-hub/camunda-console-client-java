package io.camunda.console.client.impl;

import io.camunda.console.client.api.CamundaConsoleClient.Clusters;
import io.camunda.console.client.exception.CamundaConsoleClientException;
import io.camunda.console.client.invoker.ApiException;
import io.camunda.console.client.model.CreateCluster200Response;
import io.camunda.console.client.model.CreateClusterBody;
import java.util.List;

public class ClustersImpl extends AbstractCamundaConsoleClient implements Clusters {
  public ClustersImpl(AbstractCamundaConsoleClient client) {
    super(client);
  }

  @Override
  public List<io.camunda.console.client.model.Cluster> get() {
    try {
      return getApi().getClusters();
    } catch (ApiException e) {
      throw new CamundaConsoleClientException(e);
    }
  }

  @Override
  public CreateCluster200Response post(CreateClusterBody request) {
    try {
      return getApi().createCluster(request);
    } catch (ApiException e) {
      throw new CamundaConsoleClientException(e);
    }
  }

  @Override
  public io.camunda.console.client.model.Parameters parameters() {
    try {
      return getApi().getParameters();
    } catch (ApiException e) {
      throw new CamundaConsoleClientException(e);
    }
  }
}
