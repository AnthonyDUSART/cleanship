package com.cleanship.rating.domain.model.specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

final class ContainsPostalCodeSpecificationTest {
  @Test
  void should_store_sequence() {
    ContainsPostalCodeSpecification spec = ContainsPostalCodeSpecification.of("50");

    assertEquals("50", spec.sequence());
  }

  @Test
  void should_throw_exception_when_sequence_is_null() {
    assertThrows(NullPointerException.class, () -> ContainsPostalCodeSpecification.of(null));
  }
}
