package com.cleanship.rating.domain.model.rate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.exception.EmptyRateChartException;
import com.cleanship.rating.domain.exception.OverlappingRateChartException;
import com.cleanship.rating.domain.model.constraint.DeclaredValueConstraint;
import com.cleanship.rating.domain.model.constraint.ShipmentConstraint;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.modifier.FlatSurcharge;
import com.cleanship.rating.domain.model.modifier.PriceModifier;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.pricing.AppliedPriceModifier;
import com.cleanship.rating.domain.model.pricing.PriceBreakdown;
import com.cleanship.rating.domain.model.shipment.Parcel;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Weight;
import com.cleanship.rating.domain.model.zone.Subdivision;
import com.cleanship.rating.domain.model.zone.SubdivisionName;
import com.cleanship.rating.domain.model.zone.Zone;
import com.cleanship.rating.domain.model.zone.ZoneName;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

  private static final Shipment SHIPMENT =
      Shipment.of(
          Location.of(Country.of("FR")),
          Location.of(Country.of("FR")),
          Parcel.of(Weight.of(750)),
          Money.of(99_90, Currency.of("EUR")));

  @Test
  void should_store_zone() {
    RateChart rateChart = RateChart.of(FRANCE, RATES, PRICE_MODIFIERS, SHIPMENT_CONSTRAINTS);

    assertEquals(FRANCE, rateChart.zone());
  }

  @Test
  void should_store_rates() {
    Set<Rate> rates = new HashSet<>(RATES);

    RateChart rateChart = RateChart.of(FRANCE, rates, PRICE_MODIFIERS, SHIPMENT_CONSTRAINTS);

    assertEquals(rates, rateChart.rates());
    assertNotSame(rates, rateChart.rates());
  }

  @Test
  void should_store_price_modifiers() {
    List<PriceModifier> priceModifiers = new ArrayList<>(PRICE_MODIFIERS);

    RateChart rateChart = RateChart.of(FRANCE, RATES, priceModifiers, SHIPMENT_CONSTRAINTS);

    assertEquals(priceModifiers, rateChart.priceModifiers());
    assertNotSame(priceModifiers, rateChart.priceModifiers());
  }

  @Test
  void should_store_shipment_constraints() {
    Set<ShipmentConstraint> shipmentConstraints = new HashSet<>(SHIPMENT_CONSTRAINTS);

    RateChart rateChart = RateChart.of(FRANCE, RATES, PRICE_MODIFIERS, shipmentConstraints);

    assertEquals(shipmentConstraints, rateChart.shipmentConstraints());
    assertNotSame(shipmentConstraints, rateChart.shipmentConstraints());
  }

  @Test
  void should_throw_exception_when_zone_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> RateChart.of(null, RATES, PRICE_MODIFIERS, SHIPMENT_CONSTRAINTS));
  }

  @Test
  void should_throw_exception_when_rates_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> RateChart.of(FRANCE, null, PRICE_MODIFIERS, SHIPMENT_CONSTRAINTS));
  }

  @Test
  void should_throw_exception_when_price_modifiers_is_null() {
    assertThrows(
        NullPointerException.class, () -> RateChart.of(FRANCE, RATES, null, SHIPMENT_CONSTRAINTS));
  }

  @Test
  void should_throw_exception_when_shipment_constraints_is_null() {
    assertThrows(
        NullPointerException.class, () -> RateChart.of(FRANCE, RATES, PRICE_MODIFIERS, null));
  }

  @Test
  void should_have_default_empty_price_modifiers() {
    RateChart rateChart = RateChart.of(FRANCE, RATES, SHIPMENT_CONSTRAINTS);

    assertTrue(rateChart.priceModifiers().isEmpty());
  }

  @Test
  void should_have_default_empty_shipment_constraints() {
    RateChart rateChart = RateChart.of(FRANCE, RATES, PRICE_MODIFIERS);

    assertTrue(rateChart.shipmentConstraints().isEmpty());
  }

  @Test
  void should_throw_exception_when_rates_is_empty() {
    assertThrows(EmptyRateChartException.class, () -> RateChart.of(FRANCE, Set.of()));
  }

  @Test
  void should_throw_exception_when_rates_contains_overlaps() {
    Set<Rate> rates =
        Set.of(
            FlatWeightRate.of(Weight.of(1), Weight.of(99), Money.zero(Currency.of("EUR"))),
            FlatWeightRate.of(Weight.of(100), Weight.of(200), Money.zero(Currency.of("EUR"))),
            FlatWeightRate.of(Weight.of(200), Weight.of(299), Money.zero(Currency.of("EUR"))),
            FlatWeightRate.of(Weight.of(300), Weight.of(399), Money.zero(Currency.of("EUR"))));

    assertThrows(OverlappingRateChartException.class, () -> RateChart.of(FRANCE, rates));
  }

  @Test
  void should_evaluate_successfully() {
    RateChart rateChart = RateChart.of(FRANCE, RATES, PRICE_MODIFIERS, SHIPMENT_CONSTRAINTS);

    AppliedPriceModifier expectedAppliedPriceModifier =
        AppliedPriceModifier.of(
            PRICE_MODIFIERS.getFirst(),
            Money.of(12_00, Currency.of("EUR")),
            Money.of(17_00, Currency.of("EUR")));

    Optional<PriceBreakdown> expected =
        Optional.of(
            PriceBreakdown.of(
                Money.of(12_00, Currency.of("EUR")), List.of(expectedAppliedPriceModifier)));

    assertEquals(expected, rateChart.evaluate(SHIPMENT));
  }

  @Test
  void should_throw_exception_when_evaluate_null() {
    RateChart rateChart = RateChart.of(FRANCE, RATES, PRICE_MODIFIERS, SHIPMENT_CONSTRAINTS);

    assertThrows(NullPointerException.class, () -> rateChart.evaluate(null));
  }
}
