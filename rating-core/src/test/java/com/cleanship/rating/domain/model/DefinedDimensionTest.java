package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

final class DefinedDimensionTest {
  @Test
  void should_store_length() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(20), Side.of(30), Side.of(10));

    assertEquals(Side.of(20), dimension.length());
  }

  @Test
  void should_store_width() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(20), Side.of(30), Side.of(10));

    assertEquals(Side.of(30), dimension.width());
  }

  @Test
  void should_store_height() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(20), Side.of(30), Side.of(10));

    assertEquals(Side.of(10), dimension.height());
  }

  @Test
  void should_throw_exception_when_length_is_null() {
    Side side = Side.of(1);

    assertThrows(NullPointerException.class, () -> DefinedDimension.of(null, side, side));
  }

  @Test
  void should_throw_exception_when_width_is_null() {
    Side side = Side.of(1);

    assertThrows(NullPointerException.class, () -> DefinedDimension.of(side, null, side));
  }

  @Test
  void should_throw_exception_when_height_is_null() {
    Side side = Side.of(1);

    assertThrows(NullPointerException.class, () -> DefinedDimension.of(side, side, null));
  }

  @Test
  void should_be_within_successfully() {
    DefinedDimension minInclusive = DefinedDimension.of(Side.of(1), Side.of(6), Side.of(4));
    DefinedDimension maxInclusive = DefinedDimension.of(Side.of(4), Side.of(4), Side.of(8));

    DefinedDimension dimension = DefinedDimension.of(Side.of(2), Side.of(4), Side.of(8));

    assertTrue(dimension.isWithin(minInclusive, maxInclusive));
  }

  @Test
  void should_throw_exception_when_within_with_null_min_inclusive() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));

    assertThrows(NullPointerException.class, () -> dimension.isWithin(null, dimension));
  }

  @Test
  void should_throw_exception_when_within_with_null_max_inclusive() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));

    assertThrows(NullPointerException.class, () -> dimension.isWithin(dimension, null));
  }

  @Test
  void should_not_be_within_when_dimension_exceeds_max() {
    DefinedDimension min = DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));
    DefinedDimension max = DefinedDimension.of(Side.of(5), Side.of(5), Side.of(5));
    DefinedDimension tooBig = DefinedDimension.of(Side.of(6), Side.of(2), Side.of(2));

    assertFalse(tooBig.isWithin(min, max));
  }

  @Test
  void should_throw_exception_when_within_min_inclusive_is_greater_than_max_inclusive() {
    DefinedDimension min = DefinedDimension.of(Side.of(4), Side.of(8), Side.of(3));
    DefinedDimension max = DefinedDimension.of(Side.of(5), Side.of(7), Side.of(3));
    DefinedDimension dimension = DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));

    assertThrows(IllegalArgumentException.class, () -> dimension.isWithin(min, max));
  }

  @ParameterizedTest
  @MethodSource("provideFitsIn")
  void should_fits_in_successfully(DefinedDimension dimension, DefinedDimension maxInclusive) {
    assertTrue(dimension.fitsIn(maxInclusive));
  }

  private static Stream<Arguments> provideFitsIn() {
    return Stream.of(
        Arguments.of(
            DefinedDimension.of(Side.of(3), Side.of(2), Side.of(1)),
            DefinedDimension.of(Side.of(3), Side.of(4), Side.of(1))),
        Arguments.of(
            DefinedDimension.of(Side.of(2), Side.of(2), Side.of(3)),
            DefinedDimension.of(Side.of(2), Side.of(2), Side.of(3))));
  }

  @Test
  void should_not_fits_in_successfully() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(2), Side.of(1), Side.of(2));
    DefinedDimension maxInclusive = DefinedDimension.of(Side.of(2), Side.of(1), Side.of(1));

    assertFalse(dimension.fitsIn(maxInclusive));
  }

  @Test
  void should_get_volume() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(2), Side.of(5), Side.of(4));

    Volume expected = Volume.of(dimension);

    assertEquals(expected, dimension.volume());
  }
}
