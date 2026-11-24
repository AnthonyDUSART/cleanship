package com.cleanship.rating.domain.event;

import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.Version;
import com.cleanship.rating.domain.model.rate.RateBook;
import java.time.Instant;
import java.util.Objects;

public record TariffUpdated(
    TariffId tariffId, RateBook rateBook, Version version, Instant occurredAt)
    implements DomainEvent {
  public TariffUpdated {
    Objects.requireNonNull(tariffId);
    Objects.requireNonNull(rateBook);
  }

  public static TariffUpdated of(
      TariffId tariffId, RateBook rateBook, Version version, Instant occurredAt) {
    return new TariffUpdated(tariffId, rateBook, version, occurredAt);
  }
}
