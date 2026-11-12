package com.cleanship.rating.domain.model.money;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.exception.InvalidMoneyException;
import java.util.Objects;

public record Money(long cents, Currency currency) implements Comparable<Money> {

  public Money {
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

  public Money add(Money other) {
    Objects.requireNonNull(other);

    ensureSameCurrency(other);

    return new Money(cents + other.cents, currency);
  }

  public Money subtract(Money other) {
    Objects.requireNonNull(other);

    ensureSameCurrency(other);

    return new Money(cents - other.cents, currency);
  }

  private void ensureSameCurrency(Money other) {
    if (currency.equals(other.currency)) {
      return;
    }

    throw CurrencyMismatchException.of(currency, other.currency);
  }

  @Override
  public int compareTo(Money o) {
    Objects.requireNonNull(o);

    ensureSameCurrency(o);

    return Long.compare(cents, o.cents);
  }

  public Money applyPercentage(Percentage percentage) {
    Objects.requireNonNull(percentage);

    return percentage.applyTo(this);
  }

  public boolean isGreaterThanOrEqualTo(Money other) {
    Objects.requireNonNull(other);

    ensureSameCurrency(other);

    return compareTo(other) >= 0;
  }

  public boolean isWithin(Money minInclusive, Money maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    ensureSameCurrency(minInclusive);
    ensureSameCurrency(maxInclusive);

    if (minInclusive.compareTo(maxInclusive) > 0) {
      throw new IllegalArgumentException("minInclusive cannot be greater than maxInclusive");
    }

    return compareTo(minInclusive) >= 0 && compareTo(maxInclusive) <= 0;
  }
}
