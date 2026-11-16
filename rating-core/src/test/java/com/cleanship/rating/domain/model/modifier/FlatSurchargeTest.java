package com.cleanship.rating.domain.model.modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class FlatSurchargeTest {
  @Test
  void should_store_amount() {
    Money amount = Money.of(4_00, Currency.of("EUR"));

    assertEquals(amount, FlatSurcharge.of(amount).amount());
  }
}
