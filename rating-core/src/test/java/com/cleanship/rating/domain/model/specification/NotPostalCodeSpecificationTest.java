package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class NotPostalCodeSpecificationTest {
  private static final AnyPostalCodeSpecification ANY = AnyPostalCodeSpecification.of();

  @Test
  void should_store_postal_code_specification() {
    NotPostalCodeSpecification spec = NotPostalCodeSpecification.of(ANY);

    assertEquals(ANY, spec.postalCodeSpecification());
  }

  @Test
  void should_throw_exception_when_postal_code_specification_is_null() {
    assertThrows(NullPointerException.class, () -> NotPostalCodeSpecification.of(null));
  }
}
