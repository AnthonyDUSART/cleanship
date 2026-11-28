package com.cleanship.rating.service.domain.repository;

import com.cleanship.rating.service.domain.exception.TariffNotFoundException;
import com.cleanship.rating.service.domain.model.Tariff;
import com.cleanship.rating.service.domain.model.TariffId;
import com.cleanship.rating.service.domain.model.TenantId;
import java.util.Optional;

public interface TariffRepository {
  void save(Tariff tariff);

  Optional<Tariff> find(TariffId tariffId, TenantId tenantId);

  default Tariff get(TariffId tariffId, TenantId tenantId) {

    return find(tariffId, tenantId)
        .orElseThrow(() -> TariffNotFoundException.of(tariffId, tenantId));
  }
}
