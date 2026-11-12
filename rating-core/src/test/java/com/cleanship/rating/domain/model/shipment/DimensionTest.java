package com.cleanship.rating.domain.model.shipment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class DimensionTest {
  @Test
  void should_create_defined_dimension() {
    DefinedDimension expected = DefinedDimension.of(Side.of(30), Side.of(20), Side.of(10));

    assertEquals(expected, Dimension.of(Side.of(30), Side.of(20), Side.of(10)));
  }

  @Test
  void should_create_no_dimension() {
    NoDimension expected = NoDimension.of();

    assertEquals(expected, Dimension.none());
  }
}
