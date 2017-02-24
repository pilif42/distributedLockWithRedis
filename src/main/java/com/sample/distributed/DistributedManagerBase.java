package com.sample.distributed;

import java.util.UUID;

public abstract class DistributedManagerBase {
  protected String keyRoot;
  protected String uuid;

  public DistributedManagerBase(String keyRoot) {
    this.keyRoot = keyRoot;
    this.uuid = UUID.randomUUID().toString();
  }

  protected String createKey(String key) {
    return String.format("%s:%s:%s", keyRoot, uuid, key);
  }

  protected String createGlobalKey(String key) {
    return String.format("%s:global:%s", keyRoot, key);
  }

  protected String createAllInstancesKey(String key) {
    return String.format("%s:*:%s", keyRoot, key);
  }
}

