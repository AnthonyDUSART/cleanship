package com.cleanship.rating.service.domain.event;

import com.cleanship.rating.core.model.rate.RateBook;
import com.cleanship.rating.service.domain.model.TariffId;
import com.cleanship.rating.service.domain.model.TenantId;
import com.cleanship.rating.service.domain.model.Version;
import java.time.Instant;
import java.util.Objects;

public record TariffUpdated(
    TariffId tariffId, TenantId tenantId, RateBook rateBook, Version version, Instant occurredAt)
    implements DomainEvent {
  public TariffUpdated {
    Objects.requireNonNull(tariffId);
    Objects.requireNonNull(tenantId);
    Objects.requireNonNull(rateBook);
    Objects.requireNonNull(version);
    Objects.requireNonNull(occurredAt);
  }

  public static TariffUpdated of(
      TariffId tariffId,
      TenantId tenantId,
      RateBook rateBook,
      Version version,
      Instant occurredAt) {
    return new TariffUpdated(tariffId, tenantId, rateBook, version, occurredAt);
  }
}
