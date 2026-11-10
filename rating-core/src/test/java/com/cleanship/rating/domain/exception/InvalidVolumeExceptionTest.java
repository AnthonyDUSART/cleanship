package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidVolumeExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidVolumeException exception = InvalidVolumeException.of(0);

    assertEquals(exception.getMessage(), "Volume \"0\" is invalid");
  }
}
