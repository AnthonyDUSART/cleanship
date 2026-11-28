package com.cleanship.rating.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class EmptyRateBookExceptionTest {
  @Test
  void should_have_valid_message() {
    EmptyRateBookException exception = EmptyRateBookException.of();

    assertEquals("Rate book must contain at least one rate chart", exception.getMessage());
  }
}
