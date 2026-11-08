package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import java.util.Objects;

public record Money(long cents, Currency currency) {
  public Money {
    Objects.requireNonNull(cents);
    Objects.requireNonNull(currency);
  }

  public static Money of(long cents, Currency currency) {
    return new Money(cents, currency);
  }

  public static Money zero(Currency currency) {
    return new Money(0, currency);
  }

  public Money add(Money other) {
    if (!currency.equals(other.currency)) {
      throw CurrencyMismatchException.of(currency, other.currency);
    }

    return new Money(cents + other.cents, currency);
  }
}
