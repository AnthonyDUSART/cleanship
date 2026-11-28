package com.cleanship.rating.core.exception;

import com.cleanship.rating.core.model.money.Currency;
import java.util.Objects;

public final class CurrencyMismatchException extends DomainException {
  private CurrencyMismatchException(Currency expected, Currency actual) {
    Objects.requireNonNull(expected);
    Objects.requireNonNull(actual);

    super(
        String.format(
            "Currency mismatch, expected \"%s\", actual \"%s\"", expected.code(), actual.code()));
  }

  public static CurrencyMismatchException of(Currency expected, Currency actual) {
    return new CurrencyMismatchException(expected, actual);
  }
}
