package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.exception.CurrencyMismatchException;
import com.cleanship.rating.domain.exception.InvalidMoneyException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

final class MoneyTest {
  private static final Currency EURO = Currency.of("EUR");

  private static final Currency DOLLAR = Currency.of("USD");

  @Test
  void should_store_cents() {
    assertEquals(40_00, Money.of(40_00, EURO).cents());
  }

  @Test
  void should_store_currency() {
    assertEquals(EURO, Money.of(40_00, EURO).currency());
  }

  @Test
  void should_throw_exception_when_currency_is_null() {
    assertThrows(NullPointerException.class, () -> Money.of(2_00, null));
  }

  @Test
  void should_throw_exception_when_cents_is_negative() {
    assertThrows(InvalidMoneyException.class, () -> Money.of(-2, EURO));
  }

  @Test
  void should_have_zero_cents() {
    assertEquals(0, Money.zero(EURO).cents());
  }

  @Test
  void should_add_successfully() {
    Money result = Money.of(50_25, EURO).add(Money.of(10_00, EURO));

    assertEquals(60_25, result.cents());
  }

  @Test
  void should_throw_exception_when_add_with_null() {
    assertThrows(NullPointerException.class, () -> Money.zero(EURO).add(null));
  }

  @Test
  void should_throw_exception_when_add_with_currency_mismatch() {
    Money euroAmount = Money.of(30_00, EURO);

    Money dollarAmount = Money.of(10_00, DOLLAR);

    assertThrows(CurrencyMismatchException.class, () -> euroAmount.add(dollarAmount));
  }

  @Test
  void should_subtract_successfully() {
    Money result = Money.of(50_00, EURO).subtract(Money.of(10_00, EURO));

    assertEquals(40_00, result.cents());
  }

  @Test
  void should_throw_exception_when_subtract_with_null() {
    assertThrows(NullPointerException.class, () -> Money.zero(EURO).subtract(null));
  }

  @Test
  void should_throw_exception_when_subtract_with_currency_mismatch() {
    Money euroAmount = Money.of(30_00, EURO);

    Money dollarAmount = Money.of(20_00, DOLLAR);

    assertThrows(CurrencyMismatchException.class, () -> euroAmount.subtract(dollarAmount));
  }

  @Test
  void should_apply_percentage_successfully() {
    Money amount = Money.of(50_00, EURO);

    assertEquals(25_00, amount.applyPercentage(Percentage.of(50_000)).cents());
  }

  @Test
  void should_throw_exception_when_apply_null_percentage() {
    assertThrows(NullPointerException.class, () -> Money.of(50_000, EURO).applyPercentage(null));
  }

  @ParameterizedTest
  @MethodSource("provideComparisons")
  void should_compare_to_successfully(Money a, Money b, int comparisonResult) {
    assertEquals(a.compareTo(b), comparisonResult);
  }

  private static Stream<Arguments> provideComparisons() {
    Currency euroCurrency = EURO;

    return Stream.of(
        Arguments.of(Money.zero(euroCurrency), Money.of(1, euroCurrency), -1),
        Arguments.of(Money.zero(euroCurrency), Money.zero(euroCurrency), 0),
        Arguments.of(Money.of(1, euroCurrency), Money.zero(euroCurrency), 1));
  }

  @Test
  void should_throw_exception_when_compare_to_null() {
    assertThrows(NullPointerException.class, () -> Money.zero(EURO).compareTo(null));
  }

  @Test
  void should_throw_exception_when_compare_to_with_currency_mismatch() {
    Money euroAmount = Money.zero(EURO);

    Money dollarAmount = Money.zero(DOLLAR);

    assertThrows(CurrencyMismatchException.class, () -> euroAmount.compareTo(dollarAmount));
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 49, 50})
  void should_be_greater_than_or_equal_to(int cents) {
    assertTrue(Money.of(50, EURO).isGreaterThanOrEqualTo(Money.of(cents, EURO)));
  }

  @Test
  void should_not_be_greater_than_or_equal_to() {
    assertFalse(Money.zero(EURO).isGreaterThanOrEqualTo(Money.of(1, EURO)));
  }

  @Test
  void should_throw_exception_when_is_greater_than_or_equal_to_null() {
    assertThrows(NullPointerException.class, () -> Money.zero(EURO).isGreaterThanOrEqualTo(null));
  }

  @Test
  void should_throw_exception_when_is_greater_than_or_equal_to_with_currency_mismatch() {
    assertThrows(
        CurrencyMismatchException.class,
        () -> Money.zero(EURO).isGreaterThanOrEqualTo(Money.zero(DOLLAR)));
  }

  @ParameterizedTest
  @CsvSource({"1, 1, 1", "1, 1, 2", "1, 0, 1"})
  void should_be_within(int cents, int minInclusiveInCents, int maxInclusiveInCents) {
    Money minInclusive = Money.of(minInclusiveInCents, EURO);
    Money maxInclusive = Money.of(maxInclusiveInCents, EURO);

    assertTrue(Money.of(cents, EURO).isWithin(minInclusive, maxInclusive));
  }

  @ParameterizedTest
  @CsvSource({"1, 2, 2", "1, 2, 3", "3, 1, 2"})
  void should_not_be_within(int cents, int minInclusiveInCents, int maxInclusiveInCents) {
    Money minInclusive = Money.of(minInclusiveInCents, EURO);
    Money maxInclusive = Money.of(maxInclusiveInCents, EURO);

    assertFalse(Money.of(cents, EURO).isWithin(minInclusive, maxInclusive));
  }

  @Test
  void should_throw_exception_when_is_within_null_min_inclusive() {
    assertThrows(
        NullPointerException.class, () -> Money.zero(EURO).isWithin(null, Money.zero(EURO)));
  }

  @Test
  void should_throw_exception_when_is_within_null_max_inclusive() {
    assertThrows(
        NullPointerException.class, () -> Money.zero(EURO).isWithin(Money.zero(EURO), null));
  }

  @Test
  void should_throw_exception_when_is_within_with_greater_min_inclusive_than_max_inclusive() {
    Money minInclusive = Money.of(1, EURO);
    Money maxInclusive = Money.zero(EURO);

    assertThrows(
        IllegalArgumentException.class,
        () -> Money.zero(EURO).isWithin(minInclusive, maxInclusive));
  }
}
