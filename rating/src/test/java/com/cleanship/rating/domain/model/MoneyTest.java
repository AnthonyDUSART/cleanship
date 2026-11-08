package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.exception.InvalidMoneyException;

final class MoneyTest {
  @Test
  void should_store_cents() {
    assertEquals(40_00, Money.of(40_00, Currency.of("EUR")).cents());
  }

  @Test
  void should_store_currency() {
    assertEquals(Currency.of("EUR"), Money.of(40_00, Currency.of("EUR")).currency());
  }

  @Test
  void should_throw_exception_when_currency_is_null() {
    assertThrows(NullPointerException.class, () -> Money.of(2_00, null));
  }

  @Test
  void should_throw_exception_when_cents_is_negative() {
    assertThrows(
        InvalidMoneyException.class, () -> Money.of(-2, Currency.of("EUR")));
  }

  @Test
  void should_have_zero_cents() {
    assertEquals(0, Money.zero(Currency.of("EUR")).cents());
  }

  @Test
  void should_add_successfully() {
    Money result = Money.of(50_25, Currency.of("EUR")).add(Money.of(10_00, Currency.of("EUR")));

    assertEquals(60_25, result.cents());
  }

  @Test
  void should_throw_exception_when_add_with_null() {
    assertThrows(
        NullPointerException.class, () -> Money.zero(Currency.of("EUR")).add(null));
  }

  @Test
  void should_throw_exception_when_add_with_currency_mismatch() {
    Money euroAmount = Money.of(30_00, Currency.of("EUR"));

    Money dollarAmount = Money.of(10_00, Currency.of("USD"));

    assertThrows(CurrencyMismatchException.class, () -> euroAmount.add(dollarAmount));
  }

  @Test
  void should_subtract_successfully() {
    Money result = Money.of(50_00, Currency.of("EUR")).subtract(Money.of(10_00, Currency.of("USD")));

    assertEquals(40_00, result.cents());
  }

  @Test
  void should_throw_exception_when_subtract_with_null() {
    assertThrows(
        NullPointerException.class, () -> Money.zero(Currency.of("EUR")).subtract(null));
  }

  @Test
  void should_throw_exception_when_subtract_with_currency_mismatch() {
    Money euroAmount = Money.of(30_00, Currency.of("EUR"));

    Money dollarAmount = Money.of(20_00, Currency.of("USD"));

    assertThrows(CurrencyMismatchException.class, () -> euroAmount.subtract(dollarAmount));
  }

  @ParameterizedTest
  @MethodSource("provideComparisons")
  void should_compare_to_successfully(Money a, Money b, int comparisonResult) {
    assertEquals(a.compareTo(b), comparisonResult);
  }

  private static Stream<Arguments> provideComparisons() {
    Currency euroCurrency = Currency.of("EUR");

    return Stream.of(
        Arguments.of(Money.zero(euroCurrency), Money.of(1, euroCurrency), -1),
        Arguments.of(Money.zero(euroCurrency), Money.zero(euroCurrency), 0),
        Arguments.of(Money.of(1, euroCurrency), Money.zero(euroCurrency), 1));
  }

  @Test
  void should_throw_exception_when_compare_to_null() {
    assertThrows(
        NullPointerException.class, () -> Money.zero(Currency.of("EUR")).compareTo(null));
  }

  @Test
  void should_throw_exception_when_compare_to_with_currency_mismatch() {
    Money euroAmount = Money.zero(Currency.of("EUR"));

    Money dollarAmount = Money.zero(Currency.of("USD"));

    assertThrows(
        CurrencyMismatchException.class, () -> euroAmount.compareTo(dollarAmount));
  }
}
