package com.cleanship.rating.domain.model.modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.money.Percentage;
import com.cleanship.rating.domain.model.shipment.Parcel;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Weight;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class PercentageSurchargeTest {
  private static final Shipment SHIPMENT =
      Shipment.of(
          Location.of(Country.of("FR")),
          Location.of(Country.of("FR")),
          Parcel.of(Weight.of(250)),
          Money.of(20_00, Currency.of("EUR")));

  @Test
  void should_store_percentage() {
    PercentageSurcharge surcharge = PercentageSurcharge.of(Percentage.of(10_000));

    assertEquals(Percentage.of(10_000), surcharge.percentage());
  }

  @Test
  void should_throw_exception_when_percentage_is_null() {
    assertThrows(NullPointerException.class, () -> PercentageSurcharge.of(null));
  }

  @ParameterizedTest
  @CsvSource({"10500, 499, 551", "10550, 499, 552"})
  void should_apply_successfully(
      int percentageInMilliPercent, int currentPriceInCents, int expectedInCents) {
    PercentageSurcharge surcharge = PercentageSurcharge.of(Percentage.of(percentageInMilliPercent));

    Money currentPrice = Money.of(currentPriceInCents, Currency.of("EUR"));

    Optional<Money> expected = Optional.of(Money.of(expectedInCents, Currency.of("EUR")));

    assertEquals(expected, surcharge.apply(currentPrice, SHIPMENT));
  }

  @Test
  void should_throw_exception_when_apply_with_null_current_price() {
    PercentageSurcharge surcharge = PercentageSurcharge.of(Percentage.of(10_000));

    assertThrows(NullPointerException.class, () -> surcharge.apply(null, SHIPMENT));
  }

  @Test
  void should_throw_exception_when_apply_with_null_shipment() {
    PercentageSurcharge surcharge = PercentageSurcharge.of(Percentage.of(10_000));

    assertThrows(
        NullPointerException.class,
        () -> surcharge.apply(Money.of(4_00, Currency.of("EUR")), null));
  }

  @Test
  void should_throw_exception_when_apply_with_current_price_currency_mismatch() {
    PercentageSurcharge surcharge = PercentageSurcharge.of(Percentage.of(10_000));

    assertThrows(
        CurrencyMismatchException.class,
        () -> surcharge.apply(Money.of(4_00, Currency.of("USD")), SHIPMENT));
  }
}
