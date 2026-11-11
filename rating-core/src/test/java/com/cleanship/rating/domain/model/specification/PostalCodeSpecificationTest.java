package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class PostalCodeSpecificationTest {
  private static final AnyPostalCodeSpecification ANY = AnyPostalCodeSpecification.of();

  @Test
  void should_and_successfully() {
    PostalCodeSpecification andSpec = ANY.and(ANY);

    AndPostalCodeSpecification expected = AndPostalCodeSpecification.of(ANY, ANY);

    assertEquals(expected, andSpec);
  }
}
