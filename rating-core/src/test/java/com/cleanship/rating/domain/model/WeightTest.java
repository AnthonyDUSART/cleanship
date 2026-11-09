package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class WeightTest {
  @Test
  void should_store_grams() {
    assertEquals(4_000, Weight.of(4_000).grams());
  }
}
