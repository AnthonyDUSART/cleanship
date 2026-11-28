package com.cleanship.rating.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidPercentageExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidPercentageException exception = InvalidPercentageException.of(-20_000);

    assertEquals("Percentage \"-20000\" is invalid", exception.getMessage());
  }
}
