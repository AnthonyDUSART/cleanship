package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class PostalCodeTest {
  @Test
  void should_store_value() {
    assertEquals("75000", PostalCode.of("75000").value());
  }

  @Test
  void should_throw_exception_when_value_is_null() {
    assertThrows(NullPointerException.class, () -> PostalCode.of(null));
  }
}
