package com.cleanship.rating.domain.model.modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class FlatSurchargeTest {
  @Test
  void should_store_amount() {
    Money amount = Money.of(4_00, Currency.of("EUR"));

    assertEquals(amount, FlatSurcharge.of(amount).amount());
  }

  @Test
  void should_throw_exception_when_amount_is_null() {
    assertThrows(NullPointerException.class, () -> FlatSurcharge.of(null));
  }
}
