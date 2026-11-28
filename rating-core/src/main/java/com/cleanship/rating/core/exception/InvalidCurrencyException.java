package com.cleanship.rating.core.exception;

import java.util.Objects;

public final class InvalidCurrencyException extends DomainException {
  private InvalidCurrencyException(String code) {
    Objects.requireNonNull(code);

    super(String.format("Currency \"%s\" is invalid", code));
  }

  public static InvalidCurrencyException of(String code) {
    return new InvalidCurrencyException(code);
  }
}
