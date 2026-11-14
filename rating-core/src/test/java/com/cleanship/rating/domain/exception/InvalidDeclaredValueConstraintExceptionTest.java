package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class InvalidDeclaredValueConstraintExceptionTest {
  @Test
  void should_have_valid_message() {
    InvalidDeclaredValueConstraintException exception =
        InvalidDeclaredValueConstraintException.of(
            Money.of(300_00, Currency.of("EUR")), Money.zero(Currency.of("EUR")));

    assertEquals(
        "Declared value constraint minInclusive \"30000 EUR\" cannot be greater than maxInclusive"
            + " \"0 EUR\"",
        exception.getMessage());
  }

  @Test
  void should_throw_exception_when_min_inclusive_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> InvalidDeclaredValueConstraintException.of(null, Money.zero(Currency.of("EUR"))));
  }

  @Test
  void should_throw_exception_when_max_inclusive_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> InvalidDeclaredValueConstraintException.of(Money.zero(Currency.of("EUR")), null));
  }
}
