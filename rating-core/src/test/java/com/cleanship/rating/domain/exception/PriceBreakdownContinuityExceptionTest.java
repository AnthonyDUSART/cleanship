package com.cleanship.rating.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  @Test
  void should_throw_exception_when_expected_price_before_is_null() {
    assertThrows(
        NullPointerException.class,
        () -> PriceBreakdownContinuityException.of(null, Money.zero(Currency.of("EUR"))));
  }
}
