package com.cleanship.rating.domain.model;

import java.util.Objects;
import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.exception.InvalidMoneyException;

public record Money(long cents, Currency currency) implements Comparable<Money> {
  /**
   * @throws InvalidMoneyException
   */
  public Money {
    Objects.requireNonNull(cents);
    Objects.requireNonNull(currency);

    if (cents < 0) {
      throw InvalidMoneyException.of(cents);
    }
  }

  /**
   * @throws InvalidMoneyException
   */
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
    Objects.requireNonNull(other);

    ensureSameCurrency(other);

    return new Money(cents + other.cents, currency);
  }

  /**
   * @throws CurrencyMismatchException
   */
  public Money subtract(Money other) {
    Objects.requireNonNull(other);

    ensureSameCurrency(other);

    return new Money(cents - other.cents, currency);
  }

  /**
   * @throws CurrencyMismatchException
   */
  private void ensureSameCurrency(Money other) {
    if (currency.equals(other.currency)) {
      return;
    }

    throw CurrencyMismatchException.of(currency, other.currency);
  }

  @Override
  /**
   * @throws CurrencyMismatchException
   */
  public int compareTo(Money o) {
    Objects.requireNonNull(o);

    ensureSameCurrency(o);

    return Long.compare(cents, o.cents);
  }
}
