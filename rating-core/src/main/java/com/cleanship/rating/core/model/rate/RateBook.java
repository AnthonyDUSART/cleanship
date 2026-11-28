package com.cleanship.rating.core.model.rate;

import com.cleanship.rating.core.exception.EmptyRateBookException;
import com.cleanship.rating.core.model.money.Currency;
import com.cleanship.rating.core.model.pricing.PriceBreakdown;
import com.cleanship.rating.core.model.shipment.Shipment;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

  public Optional<PriceBreakdown> evaluate(Shipment shipment) {
    Objects.requireNonNull(shipment);

    Optional<PriceBreakdown> bestPriceBreakdown = Optional.empty();

    for (RateChart rateChart : rateCharts) {
      Optional<PriceBreakdown> evaluated = rateChart.evaluate(shipment);

      if (evaluated.isPresent()
          && (bestPriceBreakdown.isEmpty()
              || evaluated.get().compareTo(bestPriceBreakdown.get()) < 0)) {
        bestPriceBreakdown = evaluated;
      }
    }

    return bestPriceBreakdown;
  }
}
