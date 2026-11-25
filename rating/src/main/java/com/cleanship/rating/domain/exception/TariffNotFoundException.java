package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.TenantId;
import java.util.Objects;

public final class TariffNotFoundException extends DomainException {
  private TariffNotFoundException(TariffId tariffId, TenantId tenantId) {
    Objects.requireNonNull(tariffId);

    super(
        String.format(
            "Tariff \"%s\" for tenant \"%s\" not found", tariffId.value(), tenantId.value()));
  }

  public static TariffNotFoundException of(TariffId tariffId, TenantId tenantId) {
    return new TariffNotFoundException(tariffId, tenantId);
  }
}
