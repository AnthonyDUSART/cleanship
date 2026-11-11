package com.cleanship.rating.domain.model.zone;

import com.cleanship.rating.domain.exception.InvalidSubdivisionNameException;
import java.util.Objects;

public record SubdivisionName(String value) {
  private static final int MAX_LENGTH = 255;

  public SubdivisionName {
    Objects.requireNonNull(value);

    value = value.strip();

    if (value.isBlank() || MAX_LENGTH < value.length()) {
      throw InvalidSubdivisionNameException.of(value);
    }
  }

  public static SubdivisionName of(String value) {
    return new SubdivisionName(value);
  }
}
