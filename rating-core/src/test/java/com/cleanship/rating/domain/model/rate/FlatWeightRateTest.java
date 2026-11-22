package com.cleanship.rating.domain.model.rate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.exception.InvalidFlatWeightRateException;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Parcel;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Weight;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class FlatWeightRateTest {
  private static final Weight MIN_INCLUSIVE = Weight.of(1);

  private static final Weight MAX_INCLUSIVE = Weight.of(249);

  private static final Money PRICE = Money.of(7_50, Currency.of("EUR"));

  private static final Shipment SHIPMENT =
      Shipment.of(
          Location.of(Country.of("FR")),
          Location.of(Country.of("FR")),
          Parcel.of(Weight.of(200)),
          Money.of(20_00, Currency.of("EUR")));

  @Test
  void should_store_min_inclusive() {
    FlatWeightRate rate = FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, PRICE);

    assertEquals(MIN_INCLUSIVE, rate.minInclusive());
  }

  @Test
  void should_store_max_inclusive() {
    FlatWeightRate rate = FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, PRICE);

    assertEquals(MAX_INCLUSIVE, rate.maxInclusive());
  }

  @Test
  void should_store_price() {
    FlatWeightRate rate = FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, PRICE);

    assertEquals(PRICE, rate.price());
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_null() {
    assertThrows(NullPointerException.class, () -> FlatWeightRate.of(null, MAX_INCLUSIVE, PRICE));
  }

  @Test
  void should_throw_exception_when_max_inclusive_is_null() {
    assertThrows(NullPointerException.class, () -> FlatWeightRate.of(MIN_INCLUSIVE, null, PRICE));
  }

  @Test
  void should_throw_exception_when_price_is_null() {
    assertThrows(
        NullPointerException.class, () -> FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, null));
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_greater_than_max_inclusive() {
    assertThrows(
        InvalidFlatWeightRateException.class,
        () -> FlatWeightRate.of(MAX_INCLUSIVE, MIN_INCLUSIVE, PRICE));
  }

  @Test
  void should_evaluate_price() {
    FlatWeightRate rate = FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, PRICE);

    assertEquals(Optional.of(PRICE), rate.evaluatePrice(SHIPMENT));
  }

  @Test
  void should_not_evaluate_price() {
    FlatWeightRate rate = FlatWeightRate.of(Weight.of(1), Weight.of(2), PRICE);

    assertEquals(Optional.empty(), rate.evaluatePrice(SHIPMENT));
  }

  @Test
  void should_throw_exception_when_evaluate_price_with_null_shipment() {
    FlatWeightRate rate = FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, PRICE);

    assertThrows(NullPointerException.class, () -> rate.evaluatePrice(null));
  }

  @ParameterizedTest
  @CsvSource({"1, 100, 100, 1000", "100, 1000, 1, 100", "1, 100, 50, 250"})
  void should_overlaps(
      int currentMinInclusiveInGrams,
      int currentMaxInclusiveInGrams,
      int otherMinInclusiveInGrams,
      int otherMaxInclusiveInGrams) {
    FlatWeightRate current =
        FlatWeightRate.of(
            Weight.of(currentMinInclusiveInGrams), Weight.of(currentMaxInclusiveInGrams), PRICE);

    FlatWeightRate other =
        FlatWeightRate.of(
            Weight.of(otherMinInclusiveInGrams), Weight.of(otherMaxInclusiveInGrams), PRICE);

    assertTrue(current.overlaps(other));
  }

  @ParameterizedTest
  @CsvSource({"1, 99, 100, 199", "100, 199, 1, 99", "1, 49, 100, 149"})
  void should_not_overlaps(
      int currentMinInclusiveInGrams,
      int currentMaxInclusiveInGrams,
      int otherMinInclusiveInGrams,
      int otherMaxInclusiveInGrams) {
    FlatWeightRate current =
        FlatWeightRate.of(
            Weight.of(currentMinInclusiveInGrams), Weight.of(currentMaxInclusiveInGrams), PRICE);

    FlatWeightRate other =
        FlatWeightRate.of(
            Weight.of(otherMinInclusiveInGrams), Weight.of(otherMaxInclusiveInGrams), PRICE);

    assertFalse(current.overlaps(other));
  }

  @Test
  void should_throw_exception_when_overlaps_null() {
    FlatWeightRate flatWeightRate = FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, PRICE);

    assertThrows(NullPointerException.class, () -> flatWeightRate.overlaps(null));
  }

  @Test
  void should_have_valid_currency() {
    FlatWeightRate flatWeightRate = FlatWeightRate.of(MIN_INCLUSIVE, MAX_INCLUSIVE, PRICE);

    assertEquals(PRICE.currency(), flatWeightRate.currency());
  }
}
