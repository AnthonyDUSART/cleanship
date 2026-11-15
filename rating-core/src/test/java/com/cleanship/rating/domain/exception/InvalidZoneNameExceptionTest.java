package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class InvalidZoneNameExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidZoneNameException exception = InvalidZoneNameException.of("America");

    assertEquals("Zone name \"America\" is invalid", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(NullPointerException.class, () -> InvalidZoneNameException.of(null));
  }
}
