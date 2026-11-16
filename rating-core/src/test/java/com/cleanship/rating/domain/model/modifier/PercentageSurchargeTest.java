package com.cleanship.rating.domain.model.modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.model.money.Percentage;
import org.junit.jupiter.api.Test;

final class PercentageSurchargeTest {
  @Test
  void should_store_percentage() {
    PercentageSurcharge surcharge = PercentageSurcharge.of(Percentage.of(10_000));

    assertEquals(Percentage.of(10_000), surcharge.percentage());
  }

  @Test
  void should_throw_exception_when_percentage_is_null() {
    assertThrows(NullPointerException.class, () -> PercentageSurcharge.of(null));
  }
}
