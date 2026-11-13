package com.cleanship.rating.domain.model.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.shipment.Volume;
import org.junit.jupiter.api.Test;

final class VolumeConstraintTest {
  private static final Volume MIN_INCLUSIVE = Volume.of(1);

  private static final Volume MAX_INCLUSIVE = Volume.of(100);

  @Test
  void should_store_min_inclusive() {
    VolumeConstraint constraint = VolumeConstraint.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(MIN_INCLUSIVE, constraint.minInclusive());
  }
}
