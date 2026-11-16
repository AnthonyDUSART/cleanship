package com.cleanship.rating.domain.model.modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class FreeThresholdDiscountTest {
  @Test
  void should_store_min_declared_value() {
    FreeThresholdDiscount discount = FreeThresholdDiscount.of(Money.zero(Currency.of("EUR")));

    assertEquals(Money.zero(Currency.of("EUR")), discount.minDeclaredValue());
  }
}
