package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidWeightException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

  @ParameterizedTest
  @MethodSource("provideComparisons")
  void should_compare_to_successfully(Weight a, Weight b, int comparisonResult) {
    assertEquals(a.compareTo(b), comparisonResult);
  }

  private static Stream<Arguments> provideComparisons() {
    return Stream.of(
        Arguments.of(Weight.of(1), Weight.of(2), -1),
        Arguments.of(Weight.of(1), Weight.of(1), 0),
        Arguments.of(Weight.of(2), Weight.of(1), 1));
  }
}
