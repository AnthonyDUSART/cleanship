package com.cleanship.rating.domain.event;

import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.TenantId;
import com.cleanship.rating.domain.model.Version;
import java.time.Instant;

public sealed interface DomainEvent permits TariffCreated, TariffUpdated {
  public TariffId tariffId();

  public TenantId tenantId();

  public Version version();

  public Instant occurredAt();
}
