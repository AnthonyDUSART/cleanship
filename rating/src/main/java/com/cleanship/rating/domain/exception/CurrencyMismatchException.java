package com.cleanship.rating.domain.exception;

import java.util.Objects;

import com.cleanship.rating.domain.model.Currency;

public class CurrencyMismatchException extends DomainException {
  private CurrencyMismatchException(Currency expected, Currency actual) {
    Objects.requireNonNull(expected);
    Objects.requireNonNull(actual);

    super(String.format("Currency mismatch, expected \"%s\", actual \"%s\"", expected.code(), actual.code()));
  }

  public static CurrencyMismatchException of(Currency expected, Currency actual) {
    return new CurrencyMismatchException(expected, actual);
  }
}
