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

  @Test
  void should_ends_with_successfully() {
    assertTrue(DefinedPostalCode.of("75000").endsWith("000"));
  }

  @Test
  void should_not_ends_with() {
    assertFalse(DefinedPostalCode.of("75001").endsWith("000"));
  }

  @Test
  void should_throw_exception_when_ends_with_null() {
    assertThrows(NullPointerException.class, () -> DefinedPostalCode.of("75000").endsWith(null));
  }

  @Test
  void should_contains_successfully() {
    assertTrue(DefinedPostalCode.of("75000").contains("50"));
  }

  @Test
  void should_not_contains() {
    assertFalse(DefinedPostalCode.of("75000").contains("505"));
  }

  @Test
  void should_throw_exception_when_contains_null() {
    assertThrows(NullPointerException.class, () -> DefinedPostalCode.of("75000").contains(null));
  }

  @Test
  void should_be_equal_to_successfully() {
    assertTrue(DefinedPostalCode.of("75000").isEqualTo("75000"));
  }

  @Test
  void should_not_be_equal_to() {
    assertFalse(DefinedPostalCode.of("75000").isEqualTo("26000"));
  }

  @Test
  void should_throw_exception_when_is_equal_to_null() {
    assertThrows(NullPointerException.class, () -> DefinedPostalCode.of("75000").isEqualTo(null));
  }
}
