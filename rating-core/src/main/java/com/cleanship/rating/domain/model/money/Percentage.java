package com.cleanship.rating.domain.model.money;

import com.cleanship.rating.domain.exception.InvalidPercentageException;
import java.util.Objects;

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

    long product = Math.multiplyExact(amount.cents(), (long) valueInMilliPercent);

    long newCents = Math.addExact(product, 50_000L) / 100_000L;

    return Money.of(newCents, amount.currency());
  }
}
