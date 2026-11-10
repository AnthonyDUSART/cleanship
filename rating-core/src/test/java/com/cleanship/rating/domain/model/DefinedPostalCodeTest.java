package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cleanship.rating.domain.exception.InvalidPostalCodeException;
import org.junit.jupiter.api.Test;

final class DefinedPostalCodeTest {
  @Test
  void should_store_value() {
    assertEquals("75000", DefinedPostalCode.of("  75000 ").value());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(NullPointerException.class, () -> DefinedPostalCode.of(null));
  }

  @Test
  void should_throw_exception_when_value_is_blank() {
    assertThrows(InvalidPostalCodeException.class, () -> DefinedPostalCode.of("  "));
  }

  @Test
  void should_throw_exception_when_value_length_is_exceeded() {
    assertThrows(InvalidPostalCodeException.class, () -> DefinedPostalCode.of("75000000000000000"));
  }

  @Test
  void should_starts_with_successfully() {
    assertTrue(DefinedPostalCode.of("75000").startsWith("75"));
  }

  @Test
  void should_not_starts_with() {
    assertFalse(DefinedPostalCode.of("75000").startsWith("000"));
  }

  @Test
  void should_throw_exception_when_starts_with_null() {
    assertThrows(NullPointerException.class, () -> DefinedPostalCode.of("75000").startsWith(null));
  }
}
