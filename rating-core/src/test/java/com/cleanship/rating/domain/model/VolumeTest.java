package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class VolumeTest {
  @Test
  void should_store_cubic_millimeters() {
    assertEquals(1, Volume.of(1).cubicMillimeters());
  }
}
