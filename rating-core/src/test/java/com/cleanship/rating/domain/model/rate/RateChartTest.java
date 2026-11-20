package com.cleanship.rating.domain.model.rate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.constraint.DeclaredValueConstraint;
import com.cleanship.rating.domain.model.constraint.ShipmentConstraint;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.modifier.FlatSurcharge;
import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Weight;
import com.cleanship.rating.domain.model.zone.Subdivision;
import com.cleanship.rating.domain.model.zone.SubdivisionName;
import com.cleanship.rating.domain.model.zone.Zone;
import com.cleanship.rating.domain.model.zone.ZoneName;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

final class RateChartTest {
  private static final Zone FRANCE =
      Zone.of(
          ZoneName.of("France"),
          Set.of(Subdivision.of(SubdivisionName.of("France"), Country.of("FR"))));

  private static final Set<Rate> RATES =
      Set.of(
          FlatWeightRate.of(Weight.of(1), Weight.of(499), Money.of(9_00, Currency.of("EUR"))),
          FlatWeightRate.of(Weight.of(500), Weight.of(1_000), Money.of(12_00, Currency.of("EUR"))));

  private static final List<PriceModifier> PRICE_MODIFIERS =
      List.of(FlatSurcharge.of(Money.of(5_00, Currency.of("EUR"))));

  private static final Set<ShipmentConstraint> SHIPMENT_CONSTRAINTS =
      Set.of(
          DeclaredValueConstraint.of(
              Money.zero(Currency.of("EUR")), Money.of(100_00, Currency.of("EUR"))));

  @Test
  void should_store_zone() {
    RateChart rateChart = RateChart.of(FRANCE, RATES, PRICE_MODIFIERS, SHIPMENT_CONSTRAINTS);

    assertEquals(FRANCE, rateChart.zone());
  }

  @Test
  void should_store_rates() {
    RateChart rateChart = RateChart.of(FRANCE, RATES, PRICE_MODIFIERS, SHIPMENT_CONSTRAINTS);

    assertEquals(RATES, rateChart.rates());
  }

  @Test
  void should_store_price_modifiers() {
    RateChart rateChart = RateChart.of(FRANCE, RATES, PRICE_MODIFIERS, SHIPMENT_CONSTRAINTS);

    assertEquals(PRICE_MODIFIERS, rateChart.priceModifiers());
  }

  @Test
  void should_store_shipment_constraints() {
    RateChart rateChart = RateChart.of(FRANCE, RATES, PRICE_MODIFIERS, SHIPMENT_CONSTRAINTS);

    assertEquals(SHIPMENT_CONSTRAINTS, rateChart.shipmentConstraints());
  }
}
