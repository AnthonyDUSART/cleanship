package com.cleanship.rating.domain.event;

import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.Version;
import java.time.Instant;

public sealed interface DomainEvent permits TariffCreated {
  public TariffId tariffId();

  public Version version();

  public Instant occurredAt();
}
