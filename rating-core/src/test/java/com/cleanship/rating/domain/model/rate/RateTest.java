package com.cleanship.rating.domain.model.rate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Weight;
import org.junit.jupiter.api.Test;

final class RateTest {
  @Test
  void should_create_flat_weight_rate() {
    Weight minInclusive = Weight.of(1);
    Weight maxInclusive = Weight.of(499);
    Money price = Money.of(30_00, Currency.of("EUR"));
    FlatWeightRate expected = FlatWeightRate.of(minInclusive, maxInclusive, price);

    assertEquals(expected, Rate.flatWeight(minInclusive, maxInclusive, price));
  }
}
