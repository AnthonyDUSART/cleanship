package com.cleanship.rating.domain.model.zone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class ZoneNameTest {
  @Test
  void should_store_value() {
    assertEquals("America", ZoneName.of("America").value());
  }
}
