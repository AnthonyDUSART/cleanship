package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.cleanship.rating.domain.model.Currency;

final class CurrencyMismatchExceptionTest {
  @Test
  void should_have_valid_message() {
    CurrencyMismatchException exception = CurrencyMismatchException.of(
        Currency.of("EUR"),
        Currency.of("USD"));

    assertEquals(exception.getMessage(), "Currency mismatch, expected \"EUR\", actual \"USD\"");
  }

  @Test
  void should_throw_exception_when_expected_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> CurrencyMismatchException.of(null, Currency.of("USD")));
  }

  @Test
  void should_throw_exception_when_actual_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> CurrencyMismatchException.of(Currency.of("EUR"), null));
  }
}
