package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class LocationTest {
  @Test
  void should_store_country() {
    assertEquals(Country.of("FR"), Location.of(Country.of("FR")).country());
  }

  @Test
  void should_store_no_postal_code() {
    assertEquals(NoPostalCode.of(), Location.of(Country.of("FR")).postalCode());
  }
}
