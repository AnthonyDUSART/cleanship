package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cleanship.rating.domain.exception.InvalidPostalCodeException;
import org.junit.jupiter.api.Test;

final class PostalCodeTest {
  @Test
  void should_store_value() {
    assertEquals("75000", PostalCode.of("  75000 ").value());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(NullPointerException.class, () -> PostalCode.of(null));
  }

  @Test
  void should_throw_exception_when_value_is_blank() {
    assertThrows(InvalidPostalCodeException.class, () -> PostalCode.of("  "));
  }

  @Test
  void should_throw_exception_when_value_length_is_exceeded() {
    assertThrows(InvalidPostalCodeException.class, () -> PostalCode.of("75000000000000000"));
  }
}
