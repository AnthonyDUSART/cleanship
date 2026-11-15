package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidSideExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidSideException exception = InvalidSideException.of(-30_000);

    assertEquals("Side \"-30000\" is invalid", exception.getMessage());
  }
}
