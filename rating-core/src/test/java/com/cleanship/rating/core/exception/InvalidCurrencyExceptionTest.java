package com.cleanship.rating.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class InvalidCurrencyExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidCurrencyException exception = InvalidCurrencyException.of("EURO");

    assertEquals("Currency \"EURO\" is invalid", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_code_is_null() {
    assertThrows(NullPointerException.class, () -> InvalidCurrencyException.of(null));
  }
}
