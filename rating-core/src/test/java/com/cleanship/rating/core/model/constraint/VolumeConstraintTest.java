package com.cleanship.rating.core.model.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.core.exception.InvalidVolumeConstraintException;
import com.cleanship.rating.core.model.location.Country;
import com.cleanship.rating.core.model.location.Location;
import com.cleanship.rating.core.model.money.Currency;
import com.cleanship.rating.core.model.money.Money;
import com.cleanship.rating.core.model.shipment.Dimension;
import com.cleanship.rating.core.model.shipment.Parcel;
import com.cleanship.rating.core.model.shipment.Shipment;
import com.cleanship.rating.core.model.shipment.Side;
import com.cleanship.rating.core.model.shipment.Volume;
import com.cleanship.rating.core.model.shipment.Weight;
import org.junit.jupiter.api.Test;

final class VolumeConstraintTest {
  private static final Volume MIN_INCLUSIVE = Volume.of(1);

  private static final Volume MAX_INCLUSIVE = Volume.of(100);

  private static final Shipment SHIPMENT =
      Shipment.of(
          Location.of(Country.of("FR")),
          Location.of(Country.of("FR")),
          Parcel.of(Weight.of(400), Dimension.of(Side.of(2), Side.of(2), Side.of(3))),
          Money.zero(Currency.of("EUR")));

  @Test
  void should_store_min_inclusive() {
    VolumeConstraint constraint = VolumeConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(MIN_INCLUSIVE, constraint.minInclusive());
  }

  @Test
  void should_store_max_inclusive() {
    VolumeConstraint constraint = VolumeConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(MAX_INCLUSIVE, constraint.maxInclusive());
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_null() {
    assertThrows(NullPointerException.class, () -> VolumeConstraint.of(null, MAX_INCLUSIVE));
  }

  @Test
  void should_throw_exception_when_max_inclusive_is_null() {
    assertThrows(NullPointerException.class, () -> VolumeConstraint.of(MIN_INCLUSIVE, null));
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_greater_than_max_inclusive() {
    assertThrows(
        InvalidVolumeConstraintException.class,
        () -> VolumeConstraint.of(MAX_INCLUSIVE, MIN_INCLUSIVE));
  }

  @Test
  void should_be_satisfied() {
    VolumeConstraint constraint = VolumeConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertTrue(constraint.isSatisfiedBy(SHIPMENT));
  }

  @Test
  void should_not_be_satisfied() {
    VolumeConstraint constraint = VolumeConstraint.of(MAX_INCLUSIVE, MAX_INCLUSIVE);

    assertFalse(constraint.isSatisfiedBy(SHIPMENT));
  }

  @Test
  void should_not_be_satisfied_with_no_dimension() {
    Shipment shipment =
        Shipment.of(
            Location.of(Country.of("FR")),
            Location.of(Country.of("FR")),
            Parcel.of(Weight.of(1), Dimension.none()),
            Money.zero(Currency.of("EUR")));

    VolumeConstraint constraint = VolumeConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertFalse(constraint.isSatisfiedBy(shipment));
  }

  @Test
  void should_throw_exception_when_is_satisfied_by_null_shipment() {
    VolumeConstraint constraint = VolumeConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertThrows(NullPointerException.class, () -> constraint.isSatisfiedBy(null));
  }
}
