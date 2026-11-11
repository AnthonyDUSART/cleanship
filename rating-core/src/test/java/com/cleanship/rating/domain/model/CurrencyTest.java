package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidCurrencyException;
import org.junit.jupiter.api.Test;

final class CurrencyTest {
  @Test
  void should_store_code() {
    assertEquals("EUR", Currency.of("EUR").code());
  }

  @Test
  void should_throw_exception_when_code_is_null() {
    assertThrows(NullPointerException.class, () -> Currency.of(null));
  }

  @Test
  void should_throw_exception_when_code_is_invalid() {
    assertThrows(InvalidCurrencyException.class, () -> Currency.of("EURO"));
  }
}
