package com.cleanship.rating.domain.model;

import com.cleanship.rating.domain.model.rate.RateBook;

public record Tariff(TariffId id, RateBook rateBook) {
  public static Tariff create(TariffId id, RateBook rateBook) {
    return new Tariff(id, rateBook);
  }
}
