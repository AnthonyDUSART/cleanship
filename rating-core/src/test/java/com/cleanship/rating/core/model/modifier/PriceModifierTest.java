package com.cleanship.rating.core.model.modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.core.model.money.Currency;
import com.cleanship.rating.core.model.money.Money;
import com.cleanship.rating.core.model.money.Percentage;
import org.junit.jupiter.api.Test;

final class PriceModifierTest {
  @Test
  void should_create_free_threshold_discount() {
    FreeThresholdDiscount expected = FreeThresholdDiscount.of(Money.zero(Currency.of("EUR")));

    assertEquals(expected, PriceModifier.freeThresholdDiscount(Money.zero(Currency.of("EUR"))));
  }

  @Test
  void should_throw_exception_when_free_threshold_discount_with_null_min_declared_value() {
    assertThrows(NullPointerException.class, () -> PriceModifier.freeThresholdDiscount(null));
  }

  @Test
  void should_create_flat_surcharge() {
    FlatSurcharge expected = FlatSurcharge.of(Money.of(4_00, Currency.of("EUR")));

    assertEquals(expected, PriceModifier.flatSurcharge(Money.of(4_00, Currency.of("EUR"))));
  }

  @Test
  void should_throw_exception_when_flat_surcharge_with_null_amount() {
    assertThrows(NullPointerException.class, () -> PriceModifier.flatSurcharge(null));
  }

  @Test
  void should_create_percentage_surcharge() {
    PercentageSurcharge expected = PercentageSurcharge.of(Percentage.of(10_000));

    assertEquals(expected, PriceModifier.percentageSurcharge(Percentage.of(10_000)));
  }

  @Test
  void should_throw_exception_when_percentage_surcharge_percentage_is_null() {
    assertThrows(NullPointerException.class, () -> PriceModifier.percentageSurcharge(null));
  }
}
