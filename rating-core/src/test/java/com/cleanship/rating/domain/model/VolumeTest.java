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
}
