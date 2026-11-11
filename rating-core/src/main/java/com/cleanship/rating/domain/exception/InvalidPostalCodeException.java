package com.cleanship.rating.domain.exception;

public final class InvalidPostalCodeException extends DomainException {
  private InvalidPostalCodeException(String value) {
    super(String.format("Postal code \"%s\" is invalid", value));
  }

  public static InvalidPostalCodeException of(String value) {
    return new InvalidPostalCodeException(value);
  }
}
