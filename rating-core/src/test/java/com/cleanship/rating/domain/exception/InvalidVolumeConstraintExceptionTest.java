package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.shipment.Volume;
import org.junit.jupiter.api.Test;

final class InvalidVolumeConstraintExceptionTest {
  private static final Volume MIN_INCLUSIVE = Volume.of(100);

  private static final Volume MAX_INCLUSIVE = Volume.of(1);

  @Test
  void should_have_valid_message() {
    InvalidVolumeConstraintException exception =
        InvalidVolumeConstraintException.of(MIN_INCLUSIVE, MAX_INCLUSIVE);

    assertEquals(
        "Volume constraint minInclusive \"100\" cannot be greater than maxInclusive \"1\"",
        exception.getMessage());
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_null() {
    assertThrows(
        NullPointerException.class, () -> InvalidVolumeConstraintException.of(null, MAX_INCLUSIVE));
  }

  @Test
  void should_throw_exception_when_max_inclusive_is_null() {
    assertThrows(
        NullPointerException.class, () -> InvalidVolumeConstraintException.of(MIN_INCLUSIVE, null));
  }
}
