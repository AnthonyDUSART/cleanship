package com.cleanship.rating.domain.model.modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Parcel;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Weight;

import org.junit.jupiter.api.Test;

final class FreeThresholdDiscountTest {
  private static final Shipment SHIPMENT = Shipment.of(
      Location.of(Country.of("FR")),
      Location.of(Country.of("FR")),
      Parcel.of(Weight.of(250)),
      Money.of(50_00, Currency.of("EUR")));

  @Test
  void should_store_min_declared_value() {
    FreeThresholdDiscount discount = FreeThresholdDiscount.of(Money.zero(Currency.of("EUR")));

    assertEquals(Money.zero(Currency.of("EUR")), discount.minDeclaredValue());
  }

  @Test
  void should_throw_exception_when_min_declared_value_is_null() {
    assertThrows(NullPointerException.class, () -> FreeThresholdDiscount.of(null));
  }

  @Test
  void should_apply_successfully() {
    Money currentPrice = Money.of(4_57, Currency.of("EUR"));

    FreeThresholdDiscount discount = FreeThresholdDiscount.of(Money.of(50_00, Currency.of("EUR")));

    Optional<Money> expected = Optional.of(Money.zero(Currency.of("EUR")));

    assertEquals(expected, discount.apply(currentPrice, SHIPMENT));
  }
}
