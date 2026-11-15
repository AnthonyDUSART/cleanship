package com.cleanship.rating.domain.exception;

import java.util.Objects;

public final class InvalidCountryException extends DomainException {
  private InvalidCountryException(String code) {
    Objects.requireNonNull(code);

    super(String.format("Country \"%s\" is invalid", code));
  }

  public static InvalidCountryException of(String code) {
    return new InvalidCountryException(code);
  }
}
