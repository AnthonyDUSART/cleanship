package com.cleanship.rating.domain.model.rate;

import com.cleanship.rating.domain.exception.EmptyRateChartException;
import com.cleanship.rating.domain.exception.OverlappingRateChartException;
import com.cleanship.rating.domain.model.constraint.ShipmentConstraint;
import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.pricing.PriceBreakdown;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.zone.Zone;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    ensureRates(zone, rates);

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

  public Optional<PriceBreakdown> evaluate(Shipment shipment) {
    Objects.requireNonNull(shipment);

    if (!isSatisfiedBy(shipment)) {
      return Optional.empty();
    }

    Optional<Money> evaluatedPrice = evaluatePrice(shipment);

    if (evaluatedPrice.isEmpty()) {
      return Optional.empty();
    }

    PriceBreakdown priceBreakdown = PriceBreakdown.of(evaluatedPrice.get());
    for (PriceModifier priceModifier : priceModifiers) {
      priceBreakdown = priceBreakdown.apply(priceModifier, shipment);
    }

    return Optional.of(priceBreakdown);
  }

  private boolean isSatisfiedBy(Shipment shipment) {
    if (!zone.isSatisfiedBy(shipment.destination())) {
      return false;
    }

    for (ShipmentConstraint shipmentConstraint : shipmentConstraints) {
      if (!shipmentConstraint.isSatisfiedBy(shipment)) {
        return false;
      }
    }

    return true;
  }

  private Optional<Money> evaluatePrice(Shipment shipment) {
    for (Rate rate : rates) {
      Optional<Money> evaluatedPrice = rate.evaluatePrice(shipment);

      if (evaluatedPrice.isPresent()) {
        return evaluatedPrice;
      }
    }

    return Optional.empty();
  }

  private static void ensureRates(Zone zone, Set<Rate> rates) {
    List<Rate> rateList = List.copyOf(rates);
    Currency expectedCurrency = rateList.getFirst().currency();

    for (int i = 0; i < rateList.size(); i++) {
      expectedCurrency.ensureSame(rateList.get(i).currency());
      for (int j = i + 1; j < rateList.size(); j++) {
        if (rateList.get(i).overlaps(rateList.get(j))) {
          throw OverlappingRateChartException.of(zone);
        }
      }
    }
  }

  public Currency currency() {
    return rates.iterator().next().currency();
  }
}
