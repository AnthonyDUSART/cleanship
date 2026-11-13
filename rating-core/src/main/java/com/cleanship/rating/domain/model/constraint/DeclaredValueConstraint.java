package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.model.money.Money;
import java.util.Objects;

public record DeclaredValueConstraint(Money minInclusive, Money maxInclusive) {
  public DeclaredValueConstraint {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);
  }

  public static DeclaredValueConstraint of(Money minInclusive, Money maxInclusive) {
    return new DeclaredValueConstraint(minInclusive, maxInclusive);
  }
}
