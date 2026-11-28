package com.cleanship.rating.core.model.money;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.core.exception.CurrencyMismatchException;
import com.cleanship.rating.core.exception.InvalidCurrencyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class CurrencyTest {
  @Test
  void should_store_code() {
    assertEquals("EUR", Currency.of("EUR").code());
  }

  @Test
  void should_throw_exception_when_code_is_null() {
    assertThrows(NullPointerException.class, () -> Currency.of(null));
  }

  @ParameterizedTest
  @ValueSource(strings = {"EU", "EURO", "eur", "EU1"})
  void should_throw_exception_when_code_is_invalid(String code) {
    assertThrows(InvalidCurrencyException.class, () -> Currency.of(code));
  }

  @Test
  void should_ensure_same_successfully() {
    assertDoesNotThrow(() -> Currency.of("EUR").ensureSame(Currency.of("EUR")));
  }

  @Test
  void should_throw_exception_when_ensure_same_with_null() {
    assertThrows(NullPointerException.class, () -> Currency.of("EUR").ensureSame(null));
  }

  @Test
  void should_throw_exception_when_ensure_same_with_currency_mismatch() {
    assertThrows(
        CurrencyMismatchException.class, () -> Currency.of("EUR").ensureSame(Currency.of("USD")));
  }
}
