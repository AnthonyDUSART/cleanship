package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.event.TariffCreated;
import com.cleanship.rating.domain.event.TariffUpdated;
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

final class TariffTest {
  private static final TariffId ID =
      TariffId.of(UUID.fromString("01a20ad9-3328-73e9-b493-46d6c3ed874b"));

  private static final RateBook RATE_BOOK =
      RateBook.of(
          Set.of(
              RateChart.of(
                  Zone.of(
                      ZoneName.of("France"),
                      Set.of(Subdivision.of(SubdivisionName.of("France"), Country.of("FR")))),
                  Set.of(
                      Rate.flatWeight(
                          Weight.of(1), Weight.of(999), Money.of(6_50, Currency.of("EUR")))))));

  private static final Instant NOW = Instant.parse("2026-11-24T00:00:00Z");

  @Test
  void should_store_id() {
    Tariff tariff = Tariff.create(ID, RATE_BOOK, NOW);

    assertEquals(ID, tariff.id());
  }

  @Test
  void should_store_rate_book() {
    Tariff tariff = Tariff.create(ID, RATE_BOOK, NOW);

    assertEquals(RATE_BOOK, tariff.rateBook());
  }

  @Test
  void should_throw_exception_when_id_is_null() {
    assertThrows(NullPointerException.class, () -> Tariff.create(null, RATE_BOOK, NOW));
  }

  @Test
  void should_throw_exception_when_rate_book_is_null() {
    assertThrows(NullPointerException.class, () -> Tariff.create(ID, null, NOW));
  }

  @Test
  void should_throw_exception_when_create_with_null_now() {
    assertThrows(NullPointerException.class, () -> Tariff.create(ID, RATE_BOOK, null));
  }

  @Test
  void should_create_with_initial_version() {
    Tariff tariff = Tariff.create(ID, RATE_BOOK, NOW);

    assertEquals(Version.initial(), tariff.version());
  }

  @Test
  void should_reconstitute_successfully() {
    Tariff tariff = Tariff.reconstitute(ID, RATE_BOOK, Version.of(10));

    assertEquals(ID, tariff.id());
    assertEquals(RATE_BOOK, tariff.rateBook());
    assertEquals(Version.of(10), tariff.version());
    assertTrue(tariff.uncommittedEvents().isEmpty());
  }

  @Test
  void should_throw_exception_when_reconstitute_with_null_version() {
    assertThrows(NullPointerException.class, () -> Tariff.reconstitute(ID, RATE_BOOK, null));
  }

  @Test
  void should_be_equals_successfully() {
    Tariff current = Tariff.create(ID, RATE_BOOK, NOW);
    Tariff other = Tariff.reconstitute(ID, RATE_BOOK, Version.of(3));

    assertTrue(current.equals(other));
  }

  @Test
  void should_not_be_equals() {
    Tariff current = Tariff.create(ID, RATE_BOOK, NOW);
    Tariff other =
        Tariff.create(
            TariffId.of(UUID.fromString("01a20edf-51ce-778e-8363-c2431ec5f043")), RATE_BOOK, NOW);

    assertFalse(current.equals(other));
  }

  @Test
  void should_have_valid_hashcode() {
    Tariff current = Tariff.create(ID, RATE_BOOK, NOW);

    int expected = ID.hashCode();

    assertEquals(expected, current.hashCode());
  }

  @Test
  void should_create_emit_tariff_created_event() {
    Tariff tariff = Tariff.create(ID, RATE_BOOK, NOW);

    TariffCreated expected = TariffCreated.of(ID, RATE_BOOK, Version.initial(), NOW);

    assertEquals(expected, tariff.uncommittedEvents().getFirst());
  }

  @Test
  void should_update_successfully() {
    Tariff tariff = Tariff.create(ID, RATE_BOOK, NOW);

    RateBook updatedRateBook =
        RateBook.of(
            Set.of(
                RateChart.of(
                    Zone.of(
                        ZoneName.of("France"),
                        Set.of(Subdivision.of(SubdivisionName.of("France"), Country.of("FR")))),
                    Set.of(
                        Rate.flatWeight(
                            Weight.of(1), Weight.of(999), Money.of(6_50, Currency.of("EUR"))),
                        Rate.flatWeight(
                            Weight.of(1_000),
                            Weight.of(1_999),
                            Money.of(12_20, Currency.of("EUR")))))));

    Tariff updatedTariff = tariff.update(updatedRateBook, NOW);

    Version expectedVersion = Version.of(2);

    TariffUpdated expected = TariffUpdated.of(ID, updatedRateBook, expectedVersion, NOW);

    assertEquals(expected, updatedTariff.uncommittedEvents().getLast());
    assertEquals(updatedRateBook, updatedTariff.rateBook());
    assertEquals(expectedVersion, updatedTariff.version());
  }
}
