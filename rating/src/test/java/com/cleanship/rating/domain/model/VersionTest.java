package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class VersionTest {
  @Test
  void should_store_value() {
    assertEquals(3, Version.of(3).value());
  }

  @Test
  void should_initial_successfully() {
    assertEquals(1, Version.initial().value());
  }
}
