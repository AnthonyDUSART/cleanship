package com.cleanship.rating.domain.model.rate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Weight;
import com.cleanship.rating.domain.model.zone.Subdivision;
import com.cleanship.rating.domain.model.zone.SubdivisionName;
import com.cleanship.rating.domain.model.zone.Zone;
import com.cleanship.rating.domain.model.zone.ZoneName;
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

  private static final RateChart RATE_CHART = RateChart.of(ZONE, RATES);

  @Test
  void should_store_rate_charts() {
    RateBook rateBook = RateBook.of(Set.of(RATE_CHART));

    assertEquals(Set.of(RATE_CHART), rateBook.rateCharts());
  }

  @Test
  void should_throw_exception_when_rate_charts_is_null() {
    assertThrows(NullPointerException.class, () -> RateBook.of(null));
  }
}
