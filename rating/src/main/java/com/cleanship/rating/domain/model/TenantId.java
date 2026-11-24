package com.cleanship.rating.domain.model;

import java.util.UUID;

public record TenantId(UUID value) {
  public static TenantId of(UUID value) {
    return new TenantId(value);
  }
}
