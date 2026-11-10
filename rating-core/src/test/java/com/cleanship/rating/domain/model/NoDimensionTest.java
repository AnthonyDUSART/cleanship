package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

final class NoDimensionTest {
  @Test
  void should_all_instances_are_same() {
    NoDimension a = NoDimension.of();
    NoDimension b = NoDimension.of();

    assertSame(a, b);
  }

  @Test
  void should_not_be_within() {
    Side side = Side.of(20);
    DefinedDimension dimension = DefinedDimension.of(side, side, side);

    assertFalse(NoDimension.of().isWithin(dimension, dimension));
  }

  @Test
  void should_not_fits_in() {
    Side minSide = Side.of(1);
    Side maxSide = Side.of(4);

    DefinedDimension dimension = DefinedDimension.of(minSide, minSide, minSide);
    DefinedDimension maxInclusive = DefinedDimension.of(maxSide, maxSide, maxSide);

    assertFalse(dimension.fitsIn(maxInclusive));
  }
}
