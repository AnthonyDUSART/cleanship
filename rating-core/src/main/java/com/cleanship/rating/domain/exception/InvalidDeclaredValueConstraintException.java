package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.money.Money;
import java.util.Objects;

public final class InvalidDeclaredValueConstraintException extends DomainException {
  private InvalidDeclaredValueConstraintException(Money minInclusive, Money maxInclusive) {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    super(
        String.format(
            "Declared value constraint minInclusive \"%d %s\" cannot be greater than maxInclusive"
                + " \"%d %s\"",
            minInclusive.cents(),
            minInclusive.currency().code(),
            maxInclusive.cents(),
            maxInclusive.currency().code()));
  }

  public static InvalidDeclaredValueConstraintException of(Money minInclusive, Money maxInclusive) {
    return new InvalidDeclaredValueConstraintException(minInclusive, maxInclusive);
  }
}
