package com.cleanship.rating.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.Tariff;
import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.TenantId;
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
