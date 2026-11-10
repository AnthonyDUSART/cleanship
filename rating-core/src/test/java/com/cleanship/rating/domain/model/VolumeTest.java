package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

  @ParameterizedTest
  @MethodSource("provideComparisons")
  void should_compare_to_successfully(Volume a, Volume b, int comparisonResult) {
    assertEquals(a.compareTo(b), comparisonResult);
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
}
