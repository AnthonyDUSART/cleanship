package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class DimensionTest {
  @Test
  void should_store_length() {
    Dimension dimension = Dimension.of(Side.of(20), Side.of(30), Side.of(10));

    assertEquals(Side.of(20), dimension.length());
  }

  @Test
  void should_store_width() {
    Dimension dimension = Dimension.of(Side.of(20), Side.of(30), Side.of(10));

    assertEquals(Side.of(30), dimension.width());
  }

  @Test
  void should_store_height() {
    Dimension dimension = Dimension.of(Side.of(20), Side.of(30), Side.of(10));

    assertEquals(Side.of(10), dimension.height());
  }
}
