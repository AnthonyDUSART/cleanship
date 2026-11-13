package com.cleanship.rating.domain.model.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.exception.InvalidVolumeConstraintException;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.Dimension;
import com.cleanship.rating.domain.model.shipment.Parcel;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Side;
import com.cleanship.rating.domain.model.shipment.Volume;
import com.cleanship.rating.domain.model.shipment.Weight;
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
}
