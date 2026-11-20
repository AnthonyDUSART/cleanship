package com.cleanship.rating.domain.model.rate;

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
  }

  public static RateChart of(
      Zone zone,
      Set<Rate> rates,
      List<PriceModifier> priceModifiers,
      Set<ShipmentConstraint> shipmentConstraints) {
    return new RateChart(zone, rates, priceModifiers, shipmentConstraints);
  }
}
