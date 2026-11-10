package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidPostalCodeException;
import java.util.Objects;

public record DefinedPostalCode(String value) implements PostalCode {
  private static final int MAX_LENGTH = 16;

  public DefinedPostalCode {
    Objects.requireNonNull(value);

    value = value.strip();

    if (value.isBlank() || MAX_LENGTH < value.length()) {
      throw InvalidPostalCodeException.of(value);
    }
  }

  public static DefinedPostalCode of(String value) {
    return new DefinedPostalCode(value);
  }

  @Override
  public boolean startsWith(String value) {
    Objects.requireNonNull(value);

    return this.value.startsWith(value);
  }
}
