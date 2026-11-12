package com.cleanship.rating.domain.model.location;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class PostalCodeTest {
  @Test
  void should_create_defined_postal_code() {
    assertEquals(DefinedPostalCode.of("75000"), PostalCode.of("75000"));
  }

  @Test
  void should_create_no_postal_code() {
    assertEquals(NoPostalCode.of(), PostalCode.none());
  }
}
