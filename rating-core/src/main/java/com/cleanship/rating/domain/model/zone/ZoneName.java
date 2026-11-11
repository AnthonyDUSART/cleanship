package com.cleanship.rating.domain.model.zone;

import java.util.Objects;

public record ZoneName(String value) {
  public ZoneName {
    Objects.requireNonNull(value);
  }

  public static ZoneName of(String value) {
    return new ZoneName(value);
  }
}
