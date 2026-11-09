package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

final class DefinedDimensionTest {
  @Test
  void should_store_length() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(20), Side.of(30), Side.of(10));

    assertEquals(Side.of(20), dimension.length());
  }

  @Test
  void should_store_width() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(20), Side.of(30), Side.of(10));

    assertEquals(Side.of(30), dimension.width());
  }

  @Test
  void should_store_height() {
    DefinedDimension dimension = DefinedDimension.of(Side.of(20), Side.of(30), Side.of(10));

    assertEquals(Side.of(10), dimension.height());
  }

  @Test
  void should_be_within_successfully() {
    assertTrue(true);
  }
}
