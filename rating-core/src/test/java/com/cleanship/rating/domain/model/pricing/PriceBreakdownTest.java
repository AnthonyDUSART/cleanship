package com.cleanship.rating.domain.model.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

final class PriceBreakdownTest {
  private static final Money BASE_PRICE = Money.of(20_00, Currency.of("EUR"));

  private static final PriceModifier FLAT_SURCHARGE =
      PriceModifier.flatSurcharge(Money.of(5_00, Currency.of("EUR")));

  @Test
  void should_store_base_price() {
    PriceBreakdown priceBreakdown = PriceBreakdown.of(BASE_PRICE);

    assertEquals(BASE_PRICE, priceBreakdown.basePrice());
  }

  @Test
  void should_throw_exception_when_applied_price_modifiers_is_null() {
    assertThrows(NullPointerException.class, () -> new PriceBreakdown(BASE_PRICE, null));
  }

  @Test
  void should_copy_of_applied_price_modifiers() {
    List<AppliedPriceModifier> appliedPriceModifiers = new ArrayList<>();
    appliedPriceModifiers.add(AppliedPriceModifier.of(FLAT_SURCHARGE, BASE_PRICE, BASE_PRICE));

    assertNotSame(
        appliedPriceModifiers,
        new PriceBreakdown(BASE_PRICE, appliedPriceModifiers).appliedPriceModifiers());
  }

  @Test
  void should_throw_exception_when_base_price_is_null() {
    assertThrows(NullPointerException.class, () -> PriceBreakdown.of(null));
  }
}
