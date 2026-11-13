package com.cleanship.rating.domain.model.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.exception.InvalidDimensionConstraintException;
import com.cleanship.rating.domain.model.location.Country;
import com.cleanship.rating.domain.model.location.Location;
import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import com.cleanship.rating.domain.model.shipment.DefinedDimension;
import com.cleanship.rating.domain.model.shipment.Dimension;
import com.cleanship.rating.domain.model.shipment.Parcel;
import com.cleanship.rating.domain.model.shipment.Shipment;
import com.cleanship.rating.domain.model.shipment.Side;
import com.cleanship.rating.domain.model.shipment.Weight;
import org.junit.jupiter.api.Test;

final class DimensionConstraintTest {

  private static final DefinedDimension MIN_INCLUSIVE =
      DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));

  private static final DefinedDimension MAX_INCLUSIVE =
      DefinedDimension.of(Side.of(10), Side.of(2), Side.of(5));

  private static final Shipment SHIPMENT =
      Shipment.of(
          Location.of(Country.of("FR")),
          Location.of(Country.of("FR")),
          Parcel.of(Weight.of(1), Dimension.of(Side.of(2), Side.of(1), Side.of(2))),
          Money.zero(Currency.of("EUR")));

  @Test
  void should_store_min_inclusive() {
    DimensionConstraint constraint = DimensionConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(MIN_INCLUSIVE, constraint.minInclusive());
  }

  @Test
  void should_store_max_inclusive() {
    DimensionConstraint constraint = DimensionConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(MAX_INCLUSIVE, constraint.maxInclusive());
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_null() {
    assertThrows(NullPointerException.class, () -> DimensionConstraint.of(null, MAX_INCLUSIVE));
  }

  @Test
  void should_throw_exception_when_max_inclusive_is_null() {
    assertThrows(NullPointerException.class, () -> DimensionConstraint.of(MIN_INCLUSIVE, null));
  }

  @Test
  void should_throw_exception_when_min_inclusive_not_fits_in_max_inclusive() {
    assertThrows(
        InvalidDimensionConstraintException.class,
        () -> DimensionConstraint.of(MAX_INCLUSIVE, MIN_INCLUSIVE));
  }

  @Test
  void should_be_satisfied() {
    DimensionConstraint constraint = DimensionConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertTrue(constraint.isSatisfiedBy(SHIPMENT));
  }

  @Test
  void should_not_be_satisfied() {
    DimensionConstraint constraint = DimensionConstraint.of(MAX_INCLUSIVE, MAX_INCLUSIVE);

    assertFalse(constraint.isSatisfiedBy(SHIPMENT));
  }
}
