package com.cleanship.rating.domain.model.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class AppliedPriceModifierTest {
  private static final PriceModifier FLAT_SURCHARGE =
      PriceModifier.flatSurcharge(Money.of(5_00, Currency.of("EUR")));

  private static final Money PRICE_BEFORE_SURCHARGE = Money.of(15_00, Currency.of("EUR"));

  private static final Money PRICE_AFTER_SURCHARGE = Money.of(10_000, Currency.of("EUR"));

  @Test
  void should_store_price_modifier() {
    AppliedPriceModifier appliedPriceModifier =
        AppliedPriceModifier.of(FLAT_SURCHARGE, PRICE_BEFORE_SURCHARGE, PRICE_AFTER_SURCHARGE);

    assertEquals(FLAT_SURCHARGE, appliedPriceModifier.priceModifier());
  }

  @Test
  void should_store_price_before() {
    AppliedPriceModifier appliedPriceModifier =
        AppliedPriceModifier.of(FLAT_SURCHARGE, PRICE_BEFORE_SURCHARGE, PRICE_AFTER_SURCHARGE);

    assertEquals(PRICE_BEFORE_SURCHARGE, appliedPriceModifier.priceBefore());
  }
}
