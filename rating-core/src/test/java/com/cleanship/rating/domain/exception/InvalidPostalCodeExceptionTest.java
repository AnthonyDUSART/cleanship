package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidPostalCodeExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidPostalCodeException exception = InvalidPostalCodeException.of("75000000000000000");

    assertEquals("Postal code \"75000000000000000\" is invalid", exception.getMessage());
  }
}
