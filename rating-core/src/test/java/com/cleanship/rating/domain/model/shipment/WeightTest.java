package com.cleanship.rating.domain.model.shipment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  @Test
  void should_throw_exception_when_compare_to_null() {
    assertThrows(NullPointerException.class, () -> Weight.of(1).compareTo(null));
  }

  @ParameterizedTest
  @MethodSource("provideWithin")
  void should_be_within_successfully(Weight weight, Weight minInclusive, Weight maxInclusive) {
    assertTrue(weight.isWithin(minInclusive, maxInclusive));
  }

  private static Stream<Arguments> provideWithin() {
    return Stream.of(
        Arguments.of(Weight.of(2), Weight.of(1), Weight.of(3)),
        Arguments.of(Weight.of(2), Weight.of(2), Weight.of(2)));
  }

  @ParameterizedTest
  @MethodSource("provideNotWithin")
  void should_not_be_within_successfully(Weight weight, Weight minInclusive, Weight maxInclusive) {
    assertFalse(weight.isWithin(minInclusive, maxInclusive));
  }

  private static Stream<Arguments> provideNotWithin() {
    return Stream.of(
        Arguments.of(Weight.of(1), Weight.of(2), Weight.of(3)),
        Arguments.of(Weight.of(3), Weight.of(1), Weight.of(2)));
  }

  @Test
  void should_throw_exception_when_is_within_null_min_inclusive() {
    Weight maxInclusive = Weight.of(1);

    assertThrows(NullPointerException.class, () -> Weight.of(1).isWithin(null, maxInclusive));
  }

  @Test
  void should_throw_exception_when_is_within_null_max_inclusive() {
    Weight minInclusive = Weight.of(1);

    assertThrows(NullPointerException.class, () -> Weight.of(1).isWithin(minInclusive, null));
  }

  @Test
  void should_throw_exception_when_within_range_is_invalid() {
    assertThrows(
        IllegalArgumentException.class, () -> Weight.of(1).isWithin(Weight.of(3), Weight.of(2)));
  }
}
