package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NoPostalCodeTest {
  @Test
  void should_all_instances_are_same() {
    assertSame(NoPostalCode.of(), NoPostalCode.of());
  }

  @Test
  void should_not_starts_with() {
    assertFalse(NoPostalCode.of().startsWith("75"));
  }

  @Test
  void should_throw_exception_when_start_with_null() {
    assertThrows(NullPointerException.class, () -> NoPostalCode.of().startsWith(null));
  }

  @Test
  void should_not_ends_with() {
    assertFalse(NoPostalCode.of().endsWith("000"));
  }

  @Test
  void should_throw_exception_when_ends_with_null() {
    assertThrows(NullPointerException.class, () -> NoPostalCode.of().endsWith(null));
  }
}
