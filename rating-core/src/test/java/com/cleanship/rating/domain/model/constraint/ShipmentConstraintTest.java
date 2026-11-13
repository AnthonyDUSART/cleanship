package com.cleanship.rating.domain.model.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class ShipmentConstraintTest {
  @Test
  void should_create_declared_value_constraint() {
    Money amount = Money.zero(Currency.of("EUR"));

    DeclaredValueConstraint expected = DeclaredValueConstraint.of(amount, amount);

    assertEquals(expected, ShipmentConstraint.declaredValue(amount, amount));
  }
}
