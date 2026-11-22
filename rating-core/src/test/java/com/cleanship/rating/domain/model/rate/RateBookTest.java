package com.cleanship.rating.domain.model.rate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.exception.EmptyRateBookException;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Weight;
import com.cleanship.rating.domain.model.zone.Subdivision;
import com.cleanship.rating.domain.model.zone.SubdivisionName;
import com.cleanship.rating.domain.model.zone.Zone;
import com.cleanship.rating.domain.model.zone.ZoneName;
import java.util.HashSet;
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

  private static final Set<RateChart> RATE_CHARTS = Set.of(RateChart.of(ZONE, RATES));

  @Test
  void should_store_rate_charts() {
    HashSet<RateChart> rateCharts = new HashSet<>(RATE_CHARTS);
    RateBook rateBook = RateBook.of(rateCharts);

    assertEquals(RATE_CHARTS, rateBook.rateCharts());
    assertNotSame(RATE_CHARTS, rateBook.rateCharts());
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
}
