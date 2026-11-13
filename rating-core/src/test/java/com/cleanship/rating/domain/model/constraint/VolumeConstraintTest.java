package com.cleanship.rating.domain.model.constraint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
