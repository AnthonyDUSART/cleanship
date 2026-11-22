package com.cleanship.rating.domain.model.rate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.exception.EmptyRateBookException;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.pricing.PriceBreakdown;
import com.cleanship.rating.domain.model.shipment.Parcel;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Weight;
import com.cleanship.rating.domain.model.zone.Subdivision;
import com.cleanship.rating.domain.model.zone.SubdivisionName;
import com.cleanship.rating.domain.model.zone.Zone;
import com.cleanship.rating.domain.model.zone.ZoneName;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;

final class RateBookTest {
  private static final Zone ZONE =
      Zone.of(
          ZoneName.of("France"),
          Set.of(Subdivision.of(SubdivisionName.of("France"), Country.of("FR"))));

  private static final Set<Rate> RATES =
      Set.of(
          Rate.flatWeight(Weight.of(1), Weight.of(499), Money.of(6_50, Currency.of("EUR"))),
          Rate.flatWeight(Weight.of(500), Weight.of(999), Money.of(9_20, Currency.of("EUR"))));

  private static final Set<Rate> BEST_RATES =
      Set.of(
          Rate.flatWeight(Weight.of(1), Weight.of(499), Money.of(4_20, Currency.of("EUR"))),
          Rate.flatWeight(Weight.of(500), Weight.of(999), Money.of(6_50, Currency.of("EUR"))));

  private static final Set<RateChart> RATE_CHARTS =
      Set.of(RateChart.of(ZONE, RATES), RateChart.of(ZONE, BEST_RATES));

  @Test
  void should_store_rate_charts() {
    HashSet<RateChart> rateCharts = new HashSet<>(RATE_CHARTS);
    RateBook rateBook = RateBook.of(rateCharts);

    assertEquals(RATE_CHARTS, rateBook.rateCharts());
    assertNotSame(rateCharts, rateBook.rateCharts());
  }

  @Test
  void should_throw_exception_when_rate_charts_is_null() {
    assertThrows(NullPointerException.class, () -> RateBook.of(null));
  }

  @Test
  void should_throw_exception_when_rate_book_is_empty() {
    assertThrows(EmptyRateBookException.class, () -> RateBook.of(Set.of()));
  }

  @Test
  void should_throw_exception_when_rate_charts_have_currency_mismatch() {
    Set<RateChart> rateCharts =
        Set.of(
            RateChart.of(ZONE, RATES),
            RateChart.of(
                ZONE,
                Set.of(
                    Rate.flatWeight(
                        Weight.of(1), Weight.of(499), Money.of(4_20, Currency.of("USD"))))));

    assertThrows(CurrencyMismatchException.class, () -> RateBook.of(rateCharts));
  }

  @Test
  void should_evaluate_successfully() {
    Shipment shipment =
        Shipment.of(
            Location.of(Country.of("FR")),
            Location.of(Country.of("FR")),
            Parcel.of(Weight.of(750)),
            Money.of(78_00, Currency.of("EUR")));

    RateBook rateBook = RateBook.of(RATE_CHARTS);

    Optional<PriceBreakdown> expected =
        Optional.of(PriceBreakdown.of(Money.of(6_50, Currency.of("EUR"))));

    assertEquals(expected, rateBook.evaluate(shipment));
  }

  @Test
  void should_not_evaluate() {
    Shipment shipment =
        Shipment.of(
            Location.of(Country.of("FR")),
            Location.of(Country.of("EN")),
            Parcel.of(Weight.of(220)),
            Money.of(100_00, Currency.of("EUR")));

    RateBook rateBook = RateBook.of(RATE_CHARTS);

    assertEquals(Optional.empty(), rateBook.evaluate(shipment));
  }

  @Test
  void should_throw_exception_when_evaluate_null_shipment() {
    RateBook rateBook = RateBook.of(RATE_CHARTS);

    assertThrows(NullPointerException.class, () -> rateBook.evaluate(null));
  }
}
