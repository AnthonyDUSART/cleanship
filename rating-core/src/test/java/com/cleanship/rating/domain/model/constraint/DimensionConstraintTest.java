package com.cleanship.rating.domain.model.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.shipment.DefinedDimension;
import com.cleanship.rating.domain.model.shipment.Side;
import org.junit.jupiter.api.Test;

final class DimensionConstraintTest {

  private static final DefinedDimension MIN_INCLUSIVE =
      DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));

  private static final DefinedDimension MAX_INCLUSIVE =
      DefinedDimension.of(Side.of(10), Side.of(2), Side.of(5));

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
}
