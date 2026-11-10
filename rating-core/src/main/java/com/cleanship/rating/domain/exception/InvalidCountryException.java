package com.cleanship.rating.domain.exception;

public final class InvalidCountryException extends DomainException {
  private InvalidCountryException(String code) {
    super(String.format("Country \"%s\" is invalid", code));
  }

  public static InvalidCountryException of(String code) {
    return new InvalidCountryException(code);
  }
}
