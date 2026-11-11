package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

final class AnyPostalCodeSpecificationTest {
  @Test
  void should_all_instances_are_same() {
    assertSame(AnyPostalCodeSpecification.of(), AnyPostalCodeSpecification.of());
  }
}
