package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class EqualsMatchingPostalCodeSpecificationTest {
  @Test
  void should_store_value() {
    EqualsMatchingPostalCodeSpecification spec = EqualsMatchingPostalCodeSpecification.of("75000");

    assertEquals("75000", spec.value());
  }
}
