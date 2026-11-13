package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.model.money.Money;

public record DeclaredValueConstraint(Money minInclusive, Money maxInclusive) {
  public static DeclaredValueConstraint of(Money minInclusive, Money maxInclusive) {
    return new DeclaredValueConstraint(minInclusive, maxInclusive);
  }
}
