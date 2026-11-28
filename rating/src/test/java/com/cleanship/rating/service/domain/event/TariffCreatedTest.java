package com.cleanship.rating.service.domain.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.core.model.location.Country;
import com.cleanship.rating.core.model.money.Currency;
import com.cleanship.rating.core.model.money.Money;
import com.cleanship.rating.core.model.rate.Rate;
import com.cleanship.rating.core.model.rate.RateBook;
import com.cleanship.rating.core.model.rate.RateChart;
import com.cleanship.rating.core.model.shipment.Weight;
import com.cleanship.rating.core.model.zone.Subdivision;
import com.cleanship.rating.core.model.zone.SubdivisionName;
import com.cleanship.rating.core.model.zone.Zone;
import com.cleanship.rating.core.model.zone.ZoneName;
import com.cleanship.rating.service.domain.model.TariffId;
import com.cleanship.rating.service.domain.model.TenantId;
import com.cleanship.rating.service.domain.model.Version;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

final class TariffCreatedTest {
  private static final TariffId TARIFF_ID =
      TariffId.of(UUID.fromString("01a20ee2-e8f6-702d-bffe-7dce8cea3ef0"));

  private static final TenantId TENANT_ID =
      TenantId.of(UUID.fromString("01a20f5f-7e06-73d1-9f28-0f9c6ad79722"));

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
    TariffCreated event = TariffCreated.of(TARIFF_ID, TENANT_ID, RATE_BOOK, VERSION, OCCURRED_AT);

    assertEquals(TARIFF_ID, event.tariffId());
  }

  @Test
  void should_store_tenant_id() {
    TariffCreated event = TariffCreated.of(TARIFF_ID, TENANT_ID, RATE_BOOK, VERSION, OCCURRED_AT);

    assertEquals(TENANT_ID, event.tenantId());
  }

  @Test
  void should_store_rate_book() {
    TariffCreated event = TariffCreated.of(TARIFF_ID, TENANT_ID, RATE_BOOK, VERSION, OCCURRED_AT);

    assertEquals(RATE_BOOK, event.rateBook());
  }

  @Test
  void should_store_version() {
    TariffCreated event = TariffCreated.of(TARIFF_ID, TENANT_ID, RATE_BOOK, VERSION, OCCURRED_AT);

    assertEquals(VERSION, event.version());
  }

  @Test
  void should_store_occurred_at() {
    TariffCreated event = TariffCreated.of(TARIFF_ID, TENANT_ID, RATE_BOOK, VERSION, OCCURRED_AT);

    assertEquals(OCCURRED_AT, event.occurredAt());
  }

  @Test
  void should_throw_exception_when_tariff_id_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> TariffCreated.of(null, TENANT_ID, RATE_BOOK, VERSION, OCCURRED_AT));
  }

  @Test
  void should_throw_exception_when_tenant_id_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> TariffCreated.of(TARIFF_ID, null, RATE_BOOK, VERSION, OCCURRED_AT));
  }

  @Test
  void should_throw_exception_when_rate_book_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> TariffCreated.of(TARIFF_ID, TENANT_ID, null, VERSION, OCCURRED_AT));
  }

  @Test
  void should_throw_exception_when_version_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> TariffCreated.of(TARIFF_ID, TENANT_ID, RATE_BOOK, null, OCCURRED_AT));
  }

  @Test
  void should_throw_exception_when_occurred_at_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> TariffCreated.of(TARIFF_ID, TENANT_ID, RATE_BOOK, VERSION, null));
  }
}
