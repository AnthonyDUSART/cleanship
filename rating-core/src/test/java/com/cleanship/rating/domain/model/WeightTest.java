package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidWeightException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class WeightTest {
  @Test
  void should_store_grams() {
    assertEquals(4_000, Weight.of(4_000).grams());
  }

  @ParameterizedTest
  @ValueSource(ints = {0, -1, -1000})
  void should_throw_exception_when_grams_is_invalid(int grams) {
    assertThrows(InvalidWeightException.class, () -> Weight.of(grams));
  }
}
