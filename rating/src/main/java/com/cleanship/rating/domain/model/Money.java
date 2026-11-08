package com.cleanship.rating.domain.model;

import java.util.Objects;
import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.exception.InvalidMoneyException;

public record Money(long cents, Currency currency) {
  public Money {
    Objects.requireNonNull(cents);
    Objects.requireNonNull(currency);

    if (cents < 0) {
      throw InvalidMoneyException.of(cents);
    }
  }

  public static Money of(long cents, Currency currency) {
    return new Money(cents, currency);
  }

  public static Money zero(Currency currency) {
    return new Money(0, currency);
  }

  /**
   * @throws CurrencyMismatchException
   */
  public Money add(Money other) {
    if (!currency.equals(other.currency)) {
      throw CurrencyMismatchException.of(currency, other.currency);
    }

    return new Money(cents + other.cents, currency);
  }

  public Money subtract(Money other) {
    return new Money(cents - other.cents, currency);
  }
}
