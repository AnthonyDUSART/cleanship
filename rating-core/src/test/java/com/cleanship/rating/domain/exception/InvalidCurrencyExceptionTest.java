package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class InvalidCurrencyExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidCurrencyException exception = InvalidCurrencyException.of("EURO");

    assertEquals("Currency \"EURO\" is invalid", exception.getMessage());
  }
}
