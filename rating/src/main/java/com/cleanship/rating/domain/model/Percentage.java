package com.cleanship.rating.domain.model;

import java.util.Objects;

import com.cleanship.rating.domain.exception.InvalidPercentageException;

public record Percentage(int valueInMilliPercent) {
  public Percentage {
    if (valueInMilliPercent < 0) {
      throw InvalidPercentageException.of(valueInMilliPercent);
    }
  }

  public static Percentage of(int valueInMilliPercent) {
    return new Percentage(valueInMilliPercent);
  }

  public Money applyTo(Money amount) {
    Objects.requireNonNull(amount);

    long newCents = (amount.cents() * ((long) valueInMilliPercent) / 100_000L);

    return Money.of(newCents, amount.currency());
  }
}
