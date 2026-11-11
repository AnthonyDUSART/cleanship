package com.cleanship.rating.domain.exception;

public final class InvalidCurrencyException extends DomainException {
  private InvalidCurrencyException(String code) {
    super(String.format("Currency \"%s\" is invalid", code));
  }

  public static InvalidCurrencyException of(String code) {
    return new InvalidCurrencyException(code);
  }
}
