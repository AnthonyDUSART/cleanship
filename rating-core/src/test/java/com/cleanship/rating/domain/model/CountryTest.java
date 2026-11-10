package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidCountryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class CountryTest {
  @Test
  void should_store_code() {
    assertEquals("FR", Country.of("FR").code());
  }

  @Test
  void should_throw_exception_when_code_is_null() {
    assertThrows(NullPointerException.class, () -> Country.of(null));
  }

  @ParameterizedTest
  @ValueSource(strings = {"F", "FRA", "F2"})
  void should_throw_exception_when_country_is_invalid(String code) {
    assertThrows(InvalidCountryException.class, () -> Country.of(code));
  }
}
