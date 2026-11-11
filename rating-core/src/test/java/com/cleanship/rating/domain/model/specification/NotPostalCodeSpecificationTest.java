package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class NotPostalCodeSpecificationTest {
  private static final AnyPostalCodeSpecification ANY = AnyPostalCodeSpecification.of();

  @Test
  void should_store_postal_code_specification() {
    NotPostalCodeSpecification spec = NotPostalCodeSpecification.of(ANY);

    assertEquals(ANY, spec.postalCodeSpecification());
  }
}
