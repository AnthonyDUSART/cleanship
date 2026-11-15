package com.cleanship.rating.domain.model.location;

import com.cleanship.rating.domain.exception.InvalidPostalCodeException;
import java.util.Locale;
import java.util.Objects;

public record DefinedPostalCode(String value) implements PostalCode {
  private static final int MAX_LENGTH = 16;

  public DefinedPostalCode {
    Objects.requireNonNull(value);

    value = value.strip().toUpperCase(Locale.ROOT);

    if (value.isBlank() || MAX_LENGTH < value.length()) {
      throw InvalidPostalCodeException.of(value);
    }
  }

  public static DefinedPostalCode of(String value) {
    return new DefinedPostalCode(value);
  }

  @Override
  public boolean startsWith(String prefix) {
    Objects.requireNonNull(prefix);

    return value.startsWith(prefix.toUpperCase(Locale.ROOT));
  }

  @Override
  public boolean endsWith(String suffix) {
    Objects.requireNonNull(suffix);

    return value.endsWith(suffix.toUpperCase(Locale.ROOT));
  }

  @Override
  public boolean contains(String sequence) {
    Objects.requireNonNull(sequence);

    return value.contains(sequence.toUpperCase(Locale.ROOT));
  }

  @Override
  public boolean isEqualTo(String value) {
    Objects.requireNonNull(value);

    return this.value.equals(value.toUpperCase(Locale.ROOT));
  }
}
