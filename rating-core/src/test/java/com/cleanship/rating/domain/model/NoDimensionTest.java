package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class NoDimensionTest {
  private static final DefinedDimension DEFINED_DIMENSION =
      DefinedDimension.of(Side.of(20), Side.of(20), Side.of(20));

  @Test
  void should_all_instances_are_same() {
    NoDimension a = NoDimension.of();
    NoDimension b = NoDimension.of();

    assertSame(a, b);
  }

  @Test
  void should_not_be_within_successfully() {
    assertFalse(NoDimension.of().isWithin(DEFINED_DIMENSION, DEFINED_DIMENSION));
  }

  @Test
  void should_throw_exception_when_is_within_with_null_min_inclusive() {
    assertThrows(
        NullPointerException.class, () -> NoDimension.of().isWithin(null, DEFINED_DIMENSION));
  }

  @Test
  void should_throw_exception_when_is_within_with_null_max_inclusive() {
    assertThrows(
        NullPointerException.class, () -> NoDimension.of().isWithin(DEFINED_DIMENSION, null));
  }

  @Test
  void should_not_fits_in_successfully() {
    assertFalse(NoDimension.of().fitsIn(DEFINED_DIMENSION));
  }

  @Test
  void should_throw_exception_when_fits_in_null() {
    assertThrows(NullPointerException.class, () -> NoDimension.of().fitsIn(null));
  }

  @Test
  void should_have_not_volume_within() {
    Volume minInclusive = Volume.of(1);
    Volume maxInclusive = Volume.of(2);

    assertFalse(NoDimension.of().isVolumeWithin(minInclusive, maxInclusive));
  }

  @Test
  void should_throw_exception_when_volume_within_null_min_inclusive() {
    Volume maxInclusive = Volume.of(2);

    assertThrows(
        NullPointerException.class, () -> NoDimension.of().isVolumeWithin(null, maxInclusive));
  }
}
