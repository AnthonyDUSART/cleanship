package com.cleanship.rating.domain.model.shipment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.exception.InvalidVolumeException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

final class VolumeTest {
  @Test
  void should_store_cubic_millimeters() {
    assertEquals(1, Volume.of(1).cubicMillimeters());
  }

  @ParameterizedTest
  @ValueSource(ints = {-1000, -1, 0})
  void should_throw_exception_when_volume_is_invalid(int cubicMillimeters) {
    assertThrows(InvalidVolumeException.class, () -> Volume.of(cubicMillimeters));
  }

  @Test
  void should_create_volume_from_defined_dimension() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(5), Side.of(2), Side.of(2));

    assertEquals(20, Volume.of(dimension).cubicMillimeters());
  }

  @Test
  void should_throw_exception_when_create_from_null_defined_dimension() {
    assertThrows(NullPointerException.class, () -> Volume.of(null));
  }

  @Test
  void should_throw_exception_when_create_from_max_defined_dimension() {
    DefinedDimension maxDimension =
        DefinedDimension.of(
            Side.of(Integer.MAX_VALUE), Side.of(Integer.MAX_VALUE), Side.of(Integer.MAX_VALUE));

    assertThrows(ArithmeticException.class, () -> Volume.of(maxDimension));
  }

  @ParameterizedTest
  @MethodSource("provideComparisons")
  void should_compare_to_successfully(Volume a, Volume b, int comparisonResult) {
    assertEquals(comparisonResult, a.compareTo(b));
  }

  private static Stream<Arguments> provideComparisons() {
    return Stream.of(
        Arguments.of(Volume.of(1), Volume.of(2), -1),
        Arguments.of(Volume.of(1), Volume.of(1), 0),
        Arguments.of(Volume.of(2), Volume.of(1), 1));
  }

  @ParameterizedTest
  @MethodSource("provideWithin")
  void should_be_within_successfully(Volume volume, Volume minInclusive, Volume maxInclusive) {
    assertTrue(volume.isWithin(minInclusive, maxInclusive));
  }

  private static Stream<Arguments> provideWithin() {
    return Stream.of(
        Arguments.of(Volume.of(1), Volume.of(1), Volume.of(2)),
        Arguments.of(Volume.of(2), Volume.of(1), Volume.of(3)),
        Arguments.of(Volume.of(2), Volume.of(1), Volume.of(2)));
  }

  @Test
  void should_not_be_within() {
    Volume minInclusive = Volume.of(1);
    Volume maxInclusive = Volume.of(2);
    Volume volume = Volume.of(3);

    assertFalse(volume.isWithin(minInclusive, maxInclusive));
  }

  @Test
  void should_throw_exception_when_compare_to_null() {
    assertThrows(NullPointerException.class, () -> Volume.of(1).compareTo(null));
  }

  @Test
  void should_throw_exception_when_within_with_null_min_inclusive() {
    assertThrows(NullPointerException.class, () -> Volume.of(2).isWithin(null, Volume.of(2)));
  }

  @Test
  void should_throw_exception_when_within_with_null_max_inclusive() {
    assertThrows(NullPointerException.class, () -> Volume.of(1).isWithin(Volume.of(1), null));
  }

  @Test
  void should_throw_exception_when_within_min_inclusive_is_greater_than_max_inclusive() {
    assertThrows(
        IllegalArgumentException.class, () -> Volume.of(2).isWithin(Volume.of(4), Volume.of(1)));
  }
}
