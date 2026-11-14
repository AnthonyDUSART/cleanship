package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class InsufficientMoneyExceptionTest {
  @Test
  void should_have_valid_message() {
    Money current = Money.zero(Currency.of("EUR"));
    Money subtrahend = Money.of(1, Currency.of("EUR"));

    InsufficientMoneyException exception = InsufficientMoneyException.of(current, subtrahend);

    assertEquals("Cannot subtract \"1 EUR\" from \"0 EUR\"", exception.getMessage());
  }

  @Test
  void should_throw_exception_when_current_is_null() {
    Money subtrahend = Money.zero(Currency.of("EUR"));

    assertThrows(NullPointerException.class, () -> InsufficientMoneyException.of(null, subtrahend));
  }
}
