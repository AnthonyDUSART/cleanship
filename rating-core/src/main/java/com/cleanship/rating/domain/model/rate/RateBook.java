package com.cleanship.rating.domain.model.rate;

import com.cleanship.rating.domain.exception.EmptyRateBookException;
import com.cleanship.rating.domain.model.money.Currency;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public record RateBook(Set<RateChart> rateCharts) {
  public RateBook {
    Objects.requireNonNull(rateCharts);

    rateCharts = Set.copyOf(rateCharts);

    if (rateCharts.isEmpty()) {
      throw EmptyRateBookException.of();
    }

    ensureRateCharts(rateCharts);
  }

  public static RateBook of(Set<RateChart> rateCharts) {
    return new RateBook(rateCharts);
  }

  private static void ensureRateCharts(Set<RateChart> rateCharts) {
    List<RateChart> rateChartList = List.copyOf(rateCharts);
    Currency expectedCurrency = rateChartList.getFirst().currency();

    for (RateChart rateChart : rateChartList) {
      expectedCurrency.ensureSame(rateChart.currency());
    }
  }
}
