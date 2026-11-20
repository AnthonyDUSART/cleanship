package com.cleanship.rating.domain.model.rate;

import com.cleanship.rating.domain.exception.EmptyRateChartException;
import com.cleanship.rating.domain.exception.OverlappingRateChartException;
import com.cleanship.rating.domain.model.constraint.ShipmentConstraint;
import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.zone.Zone;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public record RateChart(
    Zone zone,
    Set<Rate> rates,
    List<PriceModifier> priceModifiers,
    Set<ShipmentConstraint> shipmentConstraints) {
  public RateChart {
    Objects.requireNonNull(zone);
    Objects.requireNonNull(rates);
    Objects.requireNonNull(priceModifiers);
    Objects.requireNonNull(shipmentConstraints);

    rates = Set.copyOf(rates);

    if (rates.isEmpty()) {
      throw EmptyRateChartException.of(zone);
    }

    ensureNoOverlappingRates(zone, rates);

    priceModifiers = List.copyOf(priceModifiers);
    shipmentConstraints = Set.copyOf(shipmentConstraints);
  }

  public static RateChart of(
      Zone zone,
      Set<Rate> rates,
      List<PriceModifier> priceModifiers,
      Set<ShipmentConstraint> shipmentConstraints) {
    return new RateChart(zone, rates, priceModifiers, shipmentConstraints);
  }

  public static RateChart of(
      Zone zone, Set<Rate> rates, Set<ShipmentConstraint> shipmentConstraints) {
    return new RateChart(zone, rates, List.of(), shipmentConstraints);
  }

  public static RateChart of(Zone zone, Set<Rate> rates, List<PriceModifier> priceModifiers) {
    return new RateChart(zone, rates, priceModifiers, Set.of());
  }

  public static RateChart of(Zone zone, Set<Rate> rates) {
    return new RateChart(zone, rates, List.of(), Set.of());
  }

  private void ensureNoOverlappingRates(Zone zone, Set<Rate> rates) {
    List<Rate> rateList = List.copyOf(rates);
    for (int i = 0; i < rateList.size(); i++) {
      for (int j = i + 1; j < rateList.size(); j++) {
        if (rateList.get(i).overlaps(rateList.get(j))) {
          throw OverlappingRateChartException.of(zone);
        }
      }
    }
  }
}
