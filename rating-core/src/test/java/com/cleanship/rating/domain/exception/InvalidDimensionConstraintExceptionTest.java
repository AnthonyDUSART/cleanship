package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.shipment.DefinedDimension;
import com.cleanship.rating.domain.model.shipment.Side;
import org.junit.jupiter.api.Test;

final class InvalidDimensionConstraintExceptionTest {
  private static final DefinedDimension MIN_INCLUSIVE =
      DefinedDimension.of(Side.of(1), Side.of(4), Side.of(3));

  private static final DefinedDimension MAX_INCLUSIVE =
      DefinedDimension.of(Side.of(1), Side.of(1), Side.of(1));

  @Test
  void should_have_valid_message() {
    InvalidDimensionConstraintException exception =
        InvalidDimensionConstraintException.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(
        "Dimension constraint minInclusive \"4x3x1\" cannot exceed maxInclusive \"1x1x1\"",
        exception.getMessage());
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> InvalidDimensionConstraintException.of(null, MAX_INCLUSIVE));
  }

  @Test
  void should_throw_exception_when_max_inclusive_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> InvalidDimensionConstraintException.of(MIN_INCLUSIVE, null));
  }
}
