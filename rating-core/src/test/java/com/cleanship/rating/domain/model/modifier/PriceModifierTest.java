package com.cleanship.rating.domain.model.modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class PriceModifierTest {
  @Test
  void should_create_free_threshold_discount() {
    FreeThresholdDiscount expected = FreeThresholdDiscount.of(Money.zero(Currency.of("EUR")));

    assertEquals(expected, PriceModifier.freeThresholdDiscount(Money.zero(Currency.of("EUR"))));
  }
}
