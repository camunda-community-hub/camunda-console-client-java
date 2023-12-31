package io.camunda.console.client.impl;

import io.camunda.console.client.api.CamundaConsoleClient.Cluster;
import io.camunda.console.client.exception.CamundaConsoleClientException;
import io.camunda.console.client.invoker.ApiException;

public class ClusterImpl extends AbstractCluster implements Cluster {

  public ClusterImpl(AbstractCamundaConsoleClient client, String clusterId) {
    super(client, clusterId);
  }

  @Override
  public io.camunda.console.client.model.Cluster get() {
    try {
      return getApi().getCluster(getClusterId());
    } catch (ApiException e) {
      throw new CamundaConsoleClientException(e);
    }
  }

  @Override
  public void delete() {
    try {
      getApi().deleteCluster(getClusterId());
    } catch (ApiException e) {
      throw new CamundaConsoleClientException(e);
    }
  }

  @Override
  public Backups backups() {
    return new BackupsImpl(this);
  }

  @Override
  public Backup backups(String backupId) {
    return new BackupImpl(this, backupId);
  }

  @Override
  public IpWhiteList ipwhitelist() {
    return new IpWhiteListImpl(this);
  }

  @Override
  public Clients clients() {
    return new ClientsImpl(this);
  }

  @Override
  public Client clients(String clientId) {
    return new ClientImpl(this, clientId);
  }

  @Override
  public Secrets secrets() {
    return new SecretsImpl(this);
  }

  @Override
  public Secret secrets(String secretName) {
    return new SecretImpl(this, secretName);
  }
}
