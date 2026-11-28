package com.cleanship.rating.service.domain.repository;

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
import com.cleanship.rating.service.domain.exception.TariffNotFoundException;
import com.cleanship.rating.service.domain.model.Tariff;
import com.cleanship.rating.service.domain.model.TariffId;
import com.cleanship.rating.service.domain.model.TenantId;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

final class TariffRepositoryTest {
  @Test
  void should_get_successfully() {
    Tariff expected =
        Tariff.create(
            TariffId.of(UUID.fromString("01a2147f-0358-71ab-8ab0-5e1e9bea2ed6")),
            TenantId.of(UUID.fromString("01a2147f-5979-73ab-8413-d727b9bc1a2d")),
            RateBook.of(
                Set.of(
                    RateChart.of(
                        Zone.of(
                            ZoneName.of("France"),
                            Set.of(Subdivision.of(SubdivisionName.of("France"), Country.of("FR")))),
                        Set.of(
                            Rate.flatWeight(
                                Weight.of(1),
                                Weight.of(499),
                                Money.of(6_50, Currency.of("EUR"))))))),
            Instant.parse("2026-11-25T00:00:00Z"));

    assertEquals(
        expected, tariffRepository(Optional.of(expected)).get(expected.id(), expected.tenantId()));
  }

  @Test
  void should_throw_exception_when_tariff_not_found() {
    assertThrows(
        TariffNotFoundException.class,
        () ->
            tariffRepository(Optional.empty())
                .get(
                    TariffId.of(UUID.fromString("01a21484-c27f-7356-9ee7-1fb3ffbbaee0")),
                    TenantId.of(UUID.fromString("01a21485-0128-730e-a9d9-382b056b4bb6"))));
  }

  private static TariffRepository tariffRepository(Optional<Tariff> tariff) {
    return new TariffRepository() {
      @Override
      public void save(Tariff tariff) {}

      @Override
      public Optional<Tariff> find(TariffId tariffId, TenantId tenantId) {
        return tariff;
      }
    };
  }
}
