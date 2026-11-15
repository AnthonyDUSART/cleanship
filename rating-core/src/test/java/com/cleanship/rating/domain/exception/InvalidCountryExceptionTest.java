package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class InvalidCountryExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidCountryException exception = InvalidCountryException.of("FRA");

    assertEquals("Country \"FRA\" is invalid", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_code_is_null() {
    assertThrows(NullPointerException.class, () -> InvalidCountryException.of(null));
  }
}
