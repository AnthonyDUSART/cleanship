package com.cleanship.rating.domain.event;

import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.TenantId;
import com.cleanship.rating.domain.model.Version;
import com.cleanship.rating.domain.model.rate.RateBook;
import java.time.Instant;
import java.util.Objects;

public record TariffCreated(
    TariffId tariffId, TenantId tenantId, RateBook rateBook, Version version, Instant occurredAt)
    implements DomainEvent {
  public TariffCreated {
    Objects.requireNonNull(tariffId);
    Objects.requireNonNull(tenantId);
    Objects.requireNonNull(rateBook);
    Objects.requireNonNull(version);
    Objects.requireNonNull(occurredAt);
  }

  public static TariffCreated of(
      TariffId tariffId,
      TenantId tenantId,
      RateBook rateBook,
      Version version,
      Instant occurredAt) {
    return new TariffCreated(tariffId, tenantId, rateBook, version, occurredAt);
  }
}
