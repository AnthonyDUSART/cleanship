package com.cleanship.rating.service.domain.model;

import java.util.Objects;
import java.util.UUID;

public record TariffId(UUID value) {
  public TariffId {
    Objects.requireNonNull(value);
  }

  public static TariffId of(UUID value) {
    return new TariffId(value);
  }
}
