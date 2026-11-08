package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.cleanship.rating.domain.exception.InvalidPercentageException;

final class PercentageTest {
  @Test
  void should_store_value_in_milli_percent() {
    assertEquals(25_000, Percentage.of(25_000).valueInMilliPercent());
  }

  @Test
  void should_throw_exception_when_percentage_is_invalid() {
    assertThrows(
        InvalidPercentageException.class, () -> Percentage.of(-1));
  }
}
