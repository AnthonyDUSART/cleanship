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

final class FlatSurchargeTest {
  private static final Shipment SHIPMENT =
      Shipment.of(
          Location.of(Country.of("FR")),
          Location.of(Country.of("FR")),
          Parcel.of(Weight.of(250)),
          Money.of(20_00, Currency.of("EUR")));

  @Test
  void should_store_amount() {
    Money amount = Money.of(4_00, Currency.of("EUR"));

    assertEquals(amount, FlatSurcharge.of(amount).amount());
  }

  @Test
  void should_throw_exception_when_amount_is_null() {
    assertThrows(NullPointerException.class, () -> FlatSurcharge.of(null));
  }

  @Test
  void should_apply_successfully() {
    Money amount = Money.of(4_00, Currency.of("EUR"));

    FlatSurcharge surcharge = FlatSurcharge.of(amount);

    Money currentPrice = Money.of(10_00, Currency.of("EUR"));

    Optional<Money> expected = Optional.of(Money.of(14_00, Currency.of("EUR")));

    assertEquals(expected, surcharge.apply(currentPrice, SHIPMENT));
  }
}
