package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.InvalidPostalCodeException;
import java.util.Objects;

public record PostalCode(String value) {
  public PostalCode {
    Objects.requireNonNull(value);

    value = value.strip();

    if (value.isBlank()) {
      throw InvalidPostalCodeException.of(value);
    }
  }

  public static PostalCode of(String value) {
    return new PostalCode(value);
  }
}
