package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.model.rate.RateBook;
import java.util.Objects;

public record Tariff(TariffId id, RateBook rateBook) {
  public Tariff {
    Objects.requireNonNull(id);
    Objects.requireNonNull(rateBook);
  }

  public static Tariff create(TariffId id, RateBook rateBook) {
    return new Tariff(id, rateBook);
  }
}
