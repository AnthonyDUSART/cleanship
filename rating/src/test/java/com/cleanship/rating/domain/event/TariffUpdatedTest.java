package com.cleanship.rating.domain.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.Version;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.rate.Rate;
import com.cleanship.rating.domain.model.rate.RateBook;
import com.cleanship.rating.domain.model.rate.RateChart;
import com.cleanship.rating.domain.model.shipment.Weight;
import com.cleanship.rating.domain.model.zone.Subdivision;
import com.cleanship.rating.domain.model.zone.SubdivisionName;
import com.cleanship.rating.domain.model.zone.Zone;
import com.cleanship.rating.domain.model.zone.ZoneName;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

final class TariffUpdatedTest {
  private static final TariffId TARIFF_ID =
      TariffId.of(UUID.fromString("01a20f22-e8e3-73fb-bb7b-ceeb4be94bac"));

  private static final RateBook RATE_BOOK =
      RateBook.of(
          Set.of(
              RateChart.of(
                  Zone.of(
                      ZoneName.of("France"),
                      Set.of(Subdivision.of(SubdivisionName.of("France"), Country.of("FR")))),
                  Set.of(
                      Rate.flatWeight(
                          Weight.of(1), Weight.of(499), Money.of(6_20, Currency.of("EUR")))))));

  private static final Version VERSION = Version.initial();

  private static final Instant OCCURRED_AT = Instant.parse("2026-11-24T00:00:00Z");

  @Test
  void should_store_tariff_id() {
    TariffUpdated event = TariffUpdated.of(TARIFF_ID, RATE_BOOK, VERSION, OCCURRED_AT);

    assertEquals(TARIFF_ID, event.tariffId());
  }

  @Test
  void should_store_rate_book() {
    TariffUpdated event = TariffUpdated.of(TARIFF_ID, RATE_BOOK, VERSION, OCCURRED_AT);

    assertEquals(RATE_BOOK, event.rateBook());
  }

  @Test
  void should_store_version() {
    TariffUpdated event = TariffUpdated.of(TARIFF_ID, RATE_BOOK, VERSION, OCCURRED_AT);

    assertEquals(VERSION, event.version());
  }

  @Test
  void should_store_occurred_at() {
    TariffUpdated event = TariffUpdated.of(TARIFF_ID, RATE_BOOK, VERSION, OCCURRED_AT);

    assertEquals(OCCURRED_AT, event.occurredAt());
  }

  @Test
  void should_throw_exception_when_tariff_id_is_null() {
    assertThrows(
        NullPointerException.class, () -> TariffUpdated.of(null, RATE_BOOK, VERSION, OCCURRED_AT));
  }
}
