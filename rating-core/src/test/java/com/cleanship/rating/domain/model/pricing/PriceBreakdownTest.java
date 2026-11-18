package com.cleanship.rating.domain.model.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
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
}
