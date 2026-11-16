package com.cleanship.rating.domain.model.modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Parcel;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Weight;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class FreeThresholdDiscountTest {
  private static final Shipment SHIPMENT =
      Shipment.of(
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

  @ParameterizedTest
  @ValueSource(ints = {0, 40_00, 50_00})
  void should_apply_successfully(int minDeclaredValueInCents) {
    Money currentPrice = Money.of(4_57, Currency.of("EUR"));

    FreeThresholdDiscount discount =
        FreeThresholdDiscount.of(Money.of(minDeclaredValueInCents, Currency.of("EUR")));

    Optional<Money> expected = Optional.of(Money.zero(Currency.of("EUR")));

    assertEquals(expected, discount.apply(currentPrice, SHIPMENT));
  }

  @ParameterizedTest
  @ValueSource(ints = {50_01, 60_00, 100_00})
  void should_not_apply(int minDeclaredValueInCents) {
    Money currentPrice = Money.of(4_57, Currency.of("EUR"));

    FreeThresholdDiscount discount =
        FreeThresholdDiscount.of(Money.of(minDeclaredValueInCents, Currency.of("EUR")));

    assertEquals(Optional.empty(), discount.apply(currentPrice, SHIPMENT));
  }
}
