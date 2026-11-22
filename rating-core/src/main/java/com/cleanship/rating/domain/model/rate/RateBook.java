package com.cleanship.rating.domain.model.rate;

import com.cleanship.rating.domain.exception.EmptyRateBookException;
import java.util.Objects;
import java.util.Set;

public record RateBook(Set<RateChart> rateCharts) {
  public RateBook {
    Objects.requireNonNull(rateCharts);

    rateCharts = Set.copyOf(rateCharts);

    if (rateCharts.isEmpty()) {
      throw EmptyRateBookException.of();
    }
  }

  public static RateBook of(Set<RateChart> rateCharts) {
    return new RateBook(rateCharts);
  }
}
