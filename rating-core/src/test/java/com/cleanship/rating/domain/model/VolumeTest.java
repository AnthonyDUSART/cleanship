package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidVolumeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
}
