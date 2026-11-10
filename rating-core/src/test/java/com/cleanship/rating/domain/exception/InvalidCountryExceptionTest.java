package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidCountryExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidCountryException exception = InvalidCountryException.of("FRA");

    assertEquals(exception.getMessage(), "Country \"FRA\" is invalid");
  }
}
