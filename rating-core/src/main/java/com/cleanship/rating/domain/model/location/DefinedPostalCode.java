package com.cleanship.rating.domain.model.location;

import com.cleanship.rating.domain.exception.InvalidPostalCodeException;

import java.util.Objects;

record DefinedPostalCode(String value) implements PostalCode {
  private static final int MAX_LENGTH = 16;

  DefinedPostalCode {
    Objects.requireNonNull(value);

    value = value.strip();

    if (value.isBlank() || MAX_LENGTH < value.length()) {
      throw InvalidPostalCodeException.of(value);
    }
  }

  static DefinedPostalCode of(String value) {
    return new DefinedPostalCode(value);
  }

  @Override
  public boolean startsWith(String prefix) {
    Objects.requireNonNull(prefix);

    return value.startsWith(prefix);
  }

  @Override
  public boolean endsWith(String suffix) {
    Objects.requireNonNull(suffix);

    return value.endsWith(suffix);
  }

  @Override
  public boolean contains(String sequence) {
    Objects.requireNonNull(sequence);

    return value.contains(sequence);
  }

  @Override
  public boolean isEqualTo(String value) {
    Objects.requireNonNull(value);

    return this.value.equals(value);
  }
}
