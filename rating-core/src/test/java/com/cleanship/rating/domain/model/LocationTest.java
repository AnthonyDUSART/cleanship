package com.cleanship.rating.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  @Test
  void should_store_postal_code() {
    assertEquals(
        DefinedPostalCode.of("75000"),
        Location.of(Country.of("FR"), DefinedPostalCode.of("75000")).postalCode());
  }

  @Test
  void should_throw_exception_when_country_is_null() {
    assertThrows(NullPointerException.class, () -> Location.of(null));
  }

  @Test
  void should_throw_exception_when_postal_code_is_null() {
    assertThrows(NullPointerException.class, () -> Location.of(Country.of("FR"), null));
  }
}
