package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class OrPostalCodeSpecificationTest {
  private static final AnyPostalCodeSpecification ANY = AnyPostalCodeSpecification.of();

  private static final StartsWithMatchingPostalCodeSpecification STARTS_WITH_75 =
      StartsWithMatchingPostalCodeSpecification.of("75");

  private static final StartsWithMatchingPostalCodeSpecification STARTS_WITH_26 =
      StartsWithMatchingPostalCodeSpecification.of("26");

  @Test
  void should_store_left() {
    OrPostalCodeSpecification spec = OrPostalCodeSpecification.of(ANY, STARTS_WITH_26);

    assertEquals(ANY, spec.left());
  }
}
