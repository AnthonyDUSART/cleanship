package com.cleanship.rating.domain.model.rate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Weight;
import org.junit.jupiter.api.Test;

final class FlatWeightRateTest {
  private static final Weight MIN_INCLUSIVE = Weight.of(1);

  private static final Weight MAX_INCLUSIVE = Weight.of(249);

  private static final Money PRICE = Money.of(7_50, Currency.of("EUR"));

  @Test
  void should_store_min_inclusive() {
    FlatWeightRate rate = FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, PRICE);

    assertEquals(MIN_INCLUSIVE, rate.minInclusive());
  }

  @Test
  void should_store_max_inclusive() {
    FlatWeightRate rate = FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, PRICE);

    assertEquals(MAX_INCLUSIVE, rate.maxInclusive());
  }

  @Test
  void should_store_price() {
    FlatWeightRate rate = FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, PRICE);

    assertEquals(PRICE, rate.price());
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_null() {
    assertThrows(NullPointerException.class, () -> FlatWeightRate.of(null, MAX_INCLUSIVE, PRICE));
  }
}
