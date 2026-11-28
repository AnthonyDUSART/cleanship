package com.cleanship.rating.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class InvalidPostalCodeExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidPostalCodeException exception = InvalidPostalCodeException.of("75000000000000000");

    assertEquals("Postal code \"75000000000000000\" is invalid", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(NullPointerException.class, () -> InvalidPostalCodeException.of(null));
  }
}
