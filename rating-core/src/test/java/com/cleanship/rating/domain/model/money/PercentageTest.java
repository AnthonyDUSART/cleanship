package com.cleanship.rating.domain.model.money;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidPercentageException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class PercentageTest {
  @Test
  void should_store_value_in_milli_percent() {
    assertEquals(25_000, Percentage.of(25_000).valueInMilliPercent());
  }

  @Test
  void should_throw_exception_when_percentage_is_invalid() {
    assertThrows(InvalidPercentageException.class, () -> Percentage.of(-1));
  }

  @ParameterizedTest
  @CsvSource({"5000, 50000, 2500", "2257, 50000, 1129", "1, 49999, 0"})
  void should_apply_to_money_amount_successfully(
      long cents, int milliPercent, long expectedInCents) {
    Money amount = Money.of(cents, Currency.of("EUR"));

    Money expected = Money.of(expectedInCents, Currency.of("EUR"));

    assertEquals(expected, Percentage.of(milliPercent).applyTo(amount));
  }

  @Test
  void should_throw_exception_when_apply_to_null_money_amount() {
    assertThrows(NullPointerException.class, () -> Percentage.of(50_000).applyTo(null));
  }

  @Test
  void should_throw_when_apply_to_maximum_amount() {
    Money current = Money.of(Long.MAX_VALUE, Currency.of("EUR"));
    Percentage percentage = Percentage.of(1_001);

    assertThrows(ArithmeticException.class, () -> percentage.applyTo(current));
  }
}
