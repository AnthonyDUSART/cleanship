package com.cleanship.rating.core.model.modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.core.exception.CurrencyMismatchException;
import com.cleanship.rating.core.model.location.Country;
import com.cleanship.rating.core.model.location.Location;
import com.cleanship.rating.core.model.money.Currency;
import com.cleanship.rating.core.model.money.Money;
import com.cleanship.rating.core.model.shipment.Parcel;
import com.cleanship.rating.core.model.shipment.Shipment;
import com.cleanship.rating.core.model.shipment.Weight;
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

  @Test
  void should_throw_exception_when_apply_with_null_current_price() {
    FlatSurcharge surcharge = FlatSurcharge.of(Money.of(4_00, Currency.of("EUR")));

    assertThrows(NullPointerException.class, () -> surcharge.apply(null, SHIPMENT));
  }

  @Test
  void should_throw_exception_when_apply_with_null_shipment() {
    FlatSurcharge surcharge = FlatSurcharge.of(Money.of(4_00, Currency.of("EUR")));

    assertThrows(
        NullPointerException.class, () -> surcharge.apply(Money.zero(Currency.of("EUR")), null));
  }

  @Test
  void should_throw_exception_when_apply_with_current_price_currency_mismatch() {
    FlatSurcharge surcharge = FlatSurcharge.of(Money.of(4_00, Currency.of("EUR")));

    Money currentPrice = Money.zero(Currency.of("USD"));

    assertThrows(CurrencyMismatchException.class, () -> surcharge.apply(currentPrice, SHIPMENT));
  }

  @Test
  void should_throw_exception_when_apply_with_declared_value_currency_mismatch() {
    FlatSurcharge surcharge = FlatSurcharge.of(Money.of(4_00, Currency.of("USD")));

    Money currentPrice = Money.zero(Currency.of("USD"));

    assertThrows(CurrencyMismatchException.class, () -> surcharge.apply(currentPrice, SHIPMENT));
  }
}
