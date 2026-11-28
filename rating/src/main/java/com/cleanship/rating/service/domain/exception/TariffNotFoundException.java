package com.cleanship.rating.service.domain.exception;

import com.cleanship.rating.core.exception.DomainException;
import com.cleanship.rating.service.domain.model.TariffId;
import com.cleanship.rating.service.domain.model.TenantId;
import java.util.Objects;

public final class TariffNotFoundException extends DomainException {
  private TariffNotFoundException(TariffId tariffId, TenantId tenantId) {
    Objects.requireNonNull(tariffId);
    Objects.requireNonNull(tenantId);

    super(
        String.format(
            "Tariff \"%s\" for tenant \"%s\" not found", tariffId.value(), tenantId.value()));
  }

  public static TariffNotFoundException of(TariffId tariffId, TenantId tenantId) {
    return new TariffNotFoundException(tariffId, tenantId);
  }
}
