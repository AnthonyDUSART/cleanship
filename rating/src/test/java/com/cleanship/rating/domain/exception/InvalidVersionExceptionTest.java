package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidVersionExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidVersionException exception = InvalidVersionException.of(-1);

    assertEquals("Version \"-1\" is invalid", exception.getMessage());
  }
}
