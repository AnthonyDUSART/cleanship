package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class SideTest {
  @Test
  void should_store_millimeters() {
    assertEquals(30_000, Side.of(30_000).millimeters());
  }
}
