package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class InsufficientMoneyExceptionTest {
  @Test
  void should_have_valid_message() {
    Money current = Money.zero(Currency.of("EUR"));
    Money subtrahend = Money.of(1, Currency.of("EUR"));

    InsufficientMoneyException exception = InsufficientMoneyException.of(current, subtrahend);

    assertEquals("Cannot subtract \"0 EUR\" from \"1 EUR\"", exception.getMessage());
  }
}
