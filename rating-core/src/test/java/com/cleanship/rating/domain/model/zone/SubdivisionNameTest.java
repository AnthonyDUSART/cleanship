package com.cleanship.rating.domain.model.zone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class SubdivisionNameTest {
  @Test
  void should_store_value() {
    assertEquals("Paris", SubdivisionName.of("Paris").value());
  }
}
