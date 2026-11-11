package com.cleanship.rating.domain.model.zone;

import com.cleanship.rating.domain.exception.InvalidZoneNameException;
import java.util.Objects;

public record ZoneName(String value) {
  private static final int MAX_LENGTH = 255;

  public ZoneName {
    Objects.requireNonNull(value);

    value = value.strip();

    if (value.isBlank() || MAX_LENGTH < value.length()) {
      throw InvalidZoneNameException.of(value);
    }
  }

  public static ZoneName of(String value) {
    return new ZoneName(value);
  }
}
