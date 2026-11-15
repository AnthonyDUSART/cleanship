package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.shipment.Weight;
import org.junit.jupiter.api.Test;

final class InvalidFlatWeightRateExceptionTest {
  private static final Weight MIN_INCLUSIVE = Weight.of(2);

  private static final Weight MAX_INCLUSIVE = Weight.of(1);

  @Test
  void should_have_valid_message() {
    InvalidFlatWeightRateException exception =
        InvalidFlatWeightRateException.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(
        "Flat weight rate minInclusive \"2\" cannot be greater than maxInclusive \"1\"",
        exception.getMessage());
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_null() {
    assertThrows(
        NullPointerException.class, () -> InvalidFlatWeightRateException.of(null, MAX_INCLUSIVE));
  }

  @Test
  void should_throw_exception_when_max_inclusive_is_null() {
    assertThrows(
        NullPointerException.class, () -> InvalidFlatWeightRateException.of(MIN_INCLUSIVE, null));
  }
}
