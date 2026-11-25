package com.cleanship.rating.domain.exception;

import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.TenantId;

public final class TariffNotFoundException extends DomainException {
  private TariffNotFoundException(TariffId tariffId, TenantId tenantId) {
    super(
        String.format(
            "Tariff \"%s\" of tenant \"%s\" is not found", tariffId.value(), tenantId.value()));
  }

  public static TariffNotFoundException of(TariffId tariffId, TenantId tenantId) {
    return new TariffNotFoundException(tariffId, tenantId);
  }
}
