package com.cleanship.rating.domain.model.rate;

import java.util.Objects;
import java.util.Set;

public record RateBook(Set<RateChart> rateCharts) {
  public RateBook {
    Objects.requireNonNull(rateCharts);
  }

  public static RateBook of(Set<RateChart> rateCharts) {
    return new RateBook(rateCharts);
  }
}
