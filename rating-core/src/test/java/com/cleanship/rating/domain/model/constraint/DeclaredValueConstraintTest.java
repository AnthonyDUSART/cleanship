package com.cleanship.rating.domain.model.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class DeclaredValueConstraintTest {
  private static final Money MIN_INCLUSIVE = Money.zero(Currency.of("EUR"));

  private static final Money MAX_INCLUSIVE = Money.of(300_00, Currency.of("EUR"));

  @Test
  void should_store_min_inclusive() {
    DeclaredValueConstraint constraint = DeclaredValueConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(MIN_INCLUSIVE, constraint.minInclusive());
  }
}
