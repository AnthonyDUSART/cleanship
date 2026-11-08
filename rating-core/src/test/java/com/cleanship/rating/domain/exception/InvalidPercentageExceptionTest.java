package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public final class InvalidPercentageExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidPercentageException exception = InvalidPercentageException.of(-20_000);

    assertEquals(exception.getMessage(), "Percentage \"-20000\" is invalid");
  }
}
