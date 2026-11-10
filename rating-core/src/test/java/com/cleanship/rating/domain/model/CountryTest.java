package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class CountryTest {
  @Test
  void should_store_code() {
    assertEquals("FR", Country.of("FR").code());
  }
}
