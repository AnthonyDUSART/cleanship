package com.cleanship.rating.domain.model;

import java.util.UUID;

public record TariffId(UUID value) {
  public static TariffId of(UUID value) {
    return new TariffId(value);
  }
}
