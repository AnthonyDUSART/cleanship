package com.cleanship.rating.domain.model.constraint;

import com.cleanship.rating.domain.exception.InvalidDeclaredValueConstraintException;
import com.cleanship.rating.domain.model.money.Money;
import java.util.Objects;

public record DeclaredValueConstraint(Money minInclusive, Money maxInclusive) {
  public DeclaredValueConstraint {
    Objects.requireNonNull(minInclusive);
    Objects.requireNonNull(maxInclusive);

    if (minInclusive.compareTo(maxInclusive) > 0) {
      throw InvalidDeclaredValueConstraintException.of(minInclusive, maxInclusive);
    }
  }

  public static DeclaredValueConstraint of(Money minInclusive, Money maxInclusive) {
    return new DeclaredValueConstraint(minInclusive, maxInclusive);
  }
}
