package com.cleanship.rating.core.exception;

import java.util.Objects;

public final class InvalidPostalCodeException extends DomainException {
  private InvalidPostalCodeException(String value) {
    Objects.requireNonNull(value);

    super(String.format("Postal code \"%s\" is invalid", value));
  }

  public static InvalidPostalCodeException of(String value) {
    return new InvalidPostalCodeException(value);
  }
}
