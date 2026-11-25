package com.cleanship.rating.domain.repository;

import com.cleanship.rating.domain.exception.TariffNotFoundException;
import com.cleanship.rating.domain.model.Tariff;
import com.cleanship.rating.domain.model.TariffId;
import com.cleanship.rating.domain.model.TenantId;
import java.util.Optional;

public interface TariffRepository {
  void save(Tariff tariff);

  Optional<Tariff> find(TariffId tariffId, TenantId tenantId);

  default Tariff get(TariffId tariffId, TenantId tenantId) {

    return find(tariffId, tenantId)
        .orElseThrow(() -> TariffNotFoundException.of(tariffId, tenantId));
  }
}
