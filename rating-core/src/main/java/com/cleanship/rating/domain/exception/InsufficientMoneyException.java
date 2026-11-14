package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.money.Money;
import java.util.Objects;

public final class InsufficientMoneyException extends DomainException {
  private InsufficientMoneyException(Money current, Money subtrahend) {
    Objects.requireNonNull(current);

    super(
        String.format(
            "Cannot subtract \"%d %s\" from \"%d %s\"",
            subtrahend.cents(),
            subtrahend.currency().code(),
            current.cents(),
            current.currency().code()));
  }

  public static InsufficientMoneyException of(Money current, Money subtrahend) {
    return new InsufficientMoneyException(current, subtrahend);
  }
}
