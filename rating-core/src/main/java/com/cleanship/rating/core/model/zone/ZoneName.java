package com.cleanship.rating.core.model.zone;

import com.cleanship.rating.core.exception.InvalidZoneNameException;
import java.util.Objects;

public record ZoneName(String value) {
  private static final int MAX_LENGTH = 255;

  public ZoneName {
    Objects.requireNonNull(value);

    value = value.strip();

    if (value.isBlank() || value.length() > MAX_LENGTH) {
      throw InvalidZoneNameException.of(value);
    }
  }

  public static ZoneName of(String value) {
    return new ZoneName(value);
  }
}
