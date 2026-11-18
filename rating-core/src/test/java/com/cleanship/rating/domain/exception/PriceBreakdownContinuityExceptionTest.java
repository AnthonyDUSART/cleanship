package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cleanship.rating.domain.model.money.Currency;
import com.cleanship.rating.domain.model.money.Money;
import org.junit.jupiter.api.Test;

final class PriceBreakdownContinuityExceptionTest {
  @Test
  void should_have_valid_message() {
    PriceBreakdownContinuityException exception =
        PriceBreakdownContinuityException.of(
            Money.of(20_00, Currency.of("EUR")), Money.zero(Currency.of("EUR")));

    assertEquals(
        "Price breakdown expected priceBefore \"2000 EUR\", actual \"0 EUR\"",
        exception.getMessage());
  }
}
