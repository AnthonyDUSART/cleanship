package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidWeightExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidWeightException exception = InvalidWeightException.of(-300);

    assertEquals(exception.getMessage(), "Weight \"-300\" is invalid");
  }
}
